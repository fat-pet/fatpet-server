package com.example.fatpetserver.diagnosis.service

import com.example.fatpetserver.diagnosis.dto.CreateDiagnosisCommand
import com.example.fatpetserver.diagnosis.entity.Diagnosis
import com.example.fatpetserver.diagnosis.enums.Bcs
import com.example.fatpetserver.diagnosis.repository.DiagnosisRepository
import com.example.fatpetserver.pet.enums.Species
import com.example.fatpetserver.pet.service.PetQueryService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient

@Service
@Transactional
class DiagnosisCommandService(
    private val diagnosisRepository: DiagnosisRepository,
    private val diagnosisQueryService: DiagnosisQueryService,
    private val petQueryService: PetQueryService,
    @Value("\${ai.server.url}")
    private val aiServerUrl: String,
) {

    fun diagnose(command: CreateDiagnosisCommand) {
        val (petId, weight, neckCirc, chestCirc, feedAmount) = command

        val pet = petQueryService.getPetByIdOrThrow(petId)
        val breeds = pet.breeds

        val result =
            diagnoseObsity(
                age = pet.age,
                weight = weight,
                neckCirc = neckCirc,
                chestCirc = chestCirc,
                feedAmount = feedAmount,
                breeds = breeds.code,
                species = breeds.species
            )

        val bcs = Bcs.IDEAL
        val der = 1000F

        diagnosisRepository.save(
            Diagnosis(
                weight = weight,
                neckCirc = neckCirc,
                chestCirc = chestCirc,
                feedAmount = feedAmount,
                bcs = bcs,
                der = der,
                pet = pet,
            )
        )
    }

    fun diagnoseObsity(
        age: Int,
        weight: Float,
        neckCirc: Float,
        chestCirc: Float,
        feedAmount: Int,
        breeds: Int,
        species: Species,
    ) {
        val webClient = WebClient.builder()
            .baseUrl(aiServerUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        val result = webClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/api/predict")
                    .queryParam("age", age)
                    .queryParam("weight", weight)
                    .queryParam("neck_circ", neckCirc)
                    .queryParam("chest_circ", chestCirc)
                    .queryParam("food_amount", feedAmount)
                    .queryParam("breeds", breeds)
                    .queryParam("species", species.value)
                    .build()
            }
            .retrieve()
            .bodyToMono(Int::class.java)
            .subscribe { response ->
                println("predict: $response")
            }
    }

    fun test() {
        val webClient = WebClient.builder()
            .baseUrl(aiServerUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        webClient.get()
            .retrieve()
            .bodyToMono(String::class.java)
            .subscribe { response ->
                println("test: $response")
            }
    }

    fun delete(diagnosisId: Long) =
        diagnosisQueryService.getDiagnosisByIdOrThrow(diagnosisId).let { diagnosis ->
            diagnosisRepository.delete(diagnosis)
        }
}
