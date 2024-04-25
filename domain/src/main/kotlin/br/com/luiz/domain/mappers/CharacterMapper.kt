package br.com.luiz.domain.mappers

import br.com.luiz.domain.model.Character

fun Character.toCharacterList() =
    listOf(
        Character(
            id = id,
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender,
            origin = origin,
            location = location,
            image = image,
        ),
    )
