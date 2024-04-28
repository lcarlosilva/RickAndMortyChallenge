package br.com.luiz.domain.mapper

import br.com.luiz.domain.model.Character
import br.com.luiz.domain.mappers.toCharacterList
import br.com.luiz.domain.model.Location
import br.com.luiz.domain.model.Origin
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterMapperTest {

	@Test
	fun `toCharacterList returns list with single character when called on character`() {
		val character = Character(
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

		val result = character.toCharacterList()

		assertEquals(listOf(character), result)
	}

	@Test
	fun `toCharacterList returns different list instances when called multiple times`() {
		val character = Character(
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

		val result1 = character.toCharacterList()
		val result2 = character.toCharacterList()

		assert(result1 !== result2)
	}
}