package br.com.luiz.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Origin,
    var location: Location,
    var image: String,
)

@Serializable
data class Location(
    var name: String,
)

@Serializable
data class Origin(
    var name: String,
)
