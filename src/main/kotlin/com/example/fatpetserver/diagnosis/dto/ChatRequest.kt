package com.example.fatpetserver.diagnosis.dto

data class ChatRequest(
    val model: String,
    val messages: List<ChatMessage>,
)

