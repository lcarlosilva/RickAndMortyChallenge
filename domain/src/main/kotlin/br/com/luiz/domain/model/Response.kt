package br.com.luiz.domain.model

data class Response<T>(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
    val results: List<T>
)