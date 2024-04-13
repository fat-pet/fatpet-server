package com.example.fatpetserver.common

data class ApiResponse<T>(
    val message: String? = null,
    val body: T? = null,
) {
    
    companion object {
        fun error(message: String?): ApiResponse<Unit> = ApiResponse(message = message)

        fun <T> success(body: T?): ApiResponse<T> = ApiResponse(body = body)
    }
}
