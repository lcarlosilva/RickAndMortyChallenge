package br.com.luiz.rickandmortychallenge.utils

import br.com.luiz.domain.model.Character
import br.com.luiz.domain.model.Location
import br.com.luiz.domain.model.Origin

fun characterMock() = Character(
	id = 1,
	name = "Rick",
	status = "Alive",
	species = "Human",
	type = "Scientist",
	gender = "male",
	origin = Origin("Earth"),
	location = Location("Earth"),
	image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
)