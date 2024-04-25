package br.com.luiz.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: OriginResponse,
    var location: LocationResponse,
    var image: String,
)

@Serializable
data class LocationResponse(
    var name: String,
)

@Serializable
data class OriginResponse(
    var name: String,
)
