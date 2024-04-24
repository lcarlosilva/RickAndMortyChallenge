package br.com.luiz.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var image: String,
)
