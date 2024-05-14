package com.example.fatpetserver.diagnosis.service

import com.example.fatpetserver.diagnosis.entity.Diagnosis
import com.example.fatpetserver.diagnosis.enums.Bcs
import com.example.fatpetserver.diagnosis.repository.DiagnosisInfo
import com.example.fatpetserver.diagnosis.repository.DiagnosisRepository
import org.apache.coyote.BadRequestException
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient

@Service
@Transactional(readOnly = true)
class DiagnosisQueryService(
    private val diagnosisRepository: DiagnosisRepository,
    @Value("\${ai.server.url}")
    private val aiServerUrl: String,
) {

    fun getAllByPet(petId: Long): List<DiagnosisInfo> = diagnosisRepository.findAllByPetId(petId)

    fun getDiagnosisByIdOrThrow(diagnosisId: Long): Diagnosis =
        diagnosisRepository.findByIdOrNull(diagnosisId)
            ?: throw IllegalArgumentException("존재하지 않는 진단입니다.")

    fun predictBcs(
        age: Int,
        weight: Float,
        neckCirc: Float,
        chestCirc: Float,
        feedAmount: Int,
        breedsCode: Int,
        speciesCode: Int,
    ): Bcs {
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
                    .queryParam("breeds", breedsCode)
                    .queryParam("species", speciesCode)
                    .build()
            }
            .retrieve()
            .bodyToMono(Int::class.java)
            .block()

        return when (result) {
            0 -> Bcs.UNDER
            1 -> Bcs.IDEAL
            2 -> Bcs.OVER
            else -> {
                throw BadRequestException()
            }
        }
    }

    fun apiTest() {
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
}
