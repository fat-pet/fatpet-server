package com.example.fatpetserver.diagnosis.service

import com.example.fatpetserver.breed.entity.Breed
import com.example.fatpetserver.diagnosis.dto.ChatMessage
import com.example.fatpetserver.diagnosis.dto.ChatRequest
import com.example.fatpetserver.diagnosis.dto.ChatResponse
import com.example.fatpetserver.diagnosis.entity.Diagnosis
import com.example.fatpetserver.diagnosis.enums.Bcs
import com.example.fatpetserver.diagnosis.repository.DiagnosisInfo
import com.example.fatpetserver.diagnosis.repository.DiagnosisRepository
import com.example.fatpetserver.pet.entity.Pet
import org.apache.coyote.BadRequestException
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Service
@Transactional(readOnly = true)
class DiagnosisQueryService(
    private val diagnosisRepository: DiagnosisRepository,
    @Value("\${ai.server.url}")
    private val aiServerUrl: String,
    @Value("\${openai.key}")
    private val key: String,
) {

    private val chatGptUrl = "https://api.openai.com/v1/chat/completions"
    private val gptModel = "gpt-4o"

    fun getAllByPet(petId: Long): List<DiagnosisInfo> = diagnosisRepository.findAllByPetId(petId)

    fun getDiagnosisByIdOrThrow(diagnosisId: Long): Diagnosis =
        diagnosisRepository.findByIdOrNull(diagnosisId)
            ?: throw IllegalArgumentException("존재하지 않는 진단입니다.")

    fun predictBcs(
        age: Int,
        sex: Int,
        weight: Float,
        neckCirc: Float,
        chestCirc: Float,
        breedCode: Int,
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
                    .queryParam("sex", sex)
                    .queryParam("weight", weight)
                    .queryParam("neck_circ", neckCirc)
                    .queryParam("chest_circ", chestCirc)
                    .queryParam("breed", breedCode)
                    .queryParam("species", speciesCode)
                    .build()
            }
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        return when (result) {
            "0" -> Bcs.UNDER
            "1" -> Bcs.IDEAL
            "2" -> Bcs.OVER
            else -> {
                throw BadRequestException()
            }
        }
    }

    fun getGptSolution(
        pet: Pet,
        breed: Breed,
        weight: Float,
        neckCirc: Float,
        chestCirc: Float,
        bcs: Bcs,
        der: Float,
    ): String {
        val webClient = WebClient.builder()
            .baseUrl(chatGptUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer $key")
            .build()

        val request = ChatRequest(
            model = gptModel,
            messages = listOf(
                ChatMessage(role = "system", content = "너는 강아지와 고양이의 비만을 전문적으로 관리하는 수의사야."),
                ChatMessage(
                    role = "user",
                    content = "- 반려동물 정보\n" +
                            "종: ${breed.species}\n" +
                            "품종: ${breed.nameKor}\n" +
                            "나이: ${pet.ageInMonth}개월\n" +
                            "중성화: ${pet.neutered}\n" +
                            "체중: ${weight}kg\n" +
                            "목 둘레: ${neckCirc}cm\n" +
                            "가슴 둘레: ${chestCirc}cm\n" +
                            "BCS: $bcs\n" +
                            "(UNDER: 저체중, IDEAL: 정상, OVER: 과체중)\n" +
                            "DER(daily energy requirement): ${der}kcal\n" +
                            "사료 칼로리: ${pet.feedCalories}kcal / 100g\n" +
                            "이 반려동물에 대한 비만 관리 솔루션을 제공\n" +
                            "답변에 ** 같은 굵은 글자 사용 X, 입력한 반려동물의 신체정보를 그대로 출력 X\n" +
                            "아래와 같은 형식으로만 대답\n" +
                            "\n" +
                            "- AI 진단\n" +
                            "(BCS에 대한 간단한 설명과 현재 이 반려동물의 비만 상태, 주의해야할 건강 문제, 검진 내용 등 종합적인 진단)\n" +
                            "\n" +
                            "- 식습관\n" +
                            "(DER, 사료 칼로리를 기반으로 사료 급여량 조절 등 식습관 솔루션 제공)\n" +
                            "\n" +
                            "- 운동\n" +
                            "(BCS 수준에 따른 운동 솔루션 제공)]\n" +
                            "\n" +
                            "- 사료 추천\n" +
                            "(BCS 수준과 종, 품종, 나이 등에 따라 한국에서 구매 가능한 사료로 3가지 추천)"
                )
            )
        )

        val result = webClient.post()
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(ChatResponse::class.java)
            .block()

        return result?.run {
            choices[0].message.content
        } ?: throw BadRequestException()
    }

    fun apiTest(): String {
        val webClient = WebClient.builder()
            .baseUrl(aiServerUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        return webClient.get()
            .retrieve()
            .bodyToMono(String::class.java)
            .subscribe { response ->
                println("test: $response")
            }
            .toString()
    }
}
