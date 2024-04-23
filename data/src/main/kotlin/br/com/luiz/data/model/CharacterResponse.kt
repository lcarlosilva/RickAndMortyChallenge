package br.com.luiz.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse (
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var image: String
)
