package com.example.fatpetserver.diagnosis.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ChatResponse(
    val choices: List<Choice>,
)

