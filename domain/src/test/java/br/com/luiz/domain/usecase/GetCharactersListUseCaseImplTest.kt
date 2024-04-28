package br.com.luiz.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.map
import br.com.luiz.domain.model.Character
import br.com.luiz.domain.model.Location
import br.com.luiz.domain.model.Origin
import br.com.luiz.domain.pagingSource.CharacterPagingSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalPagingApi
class GetCharactersListUseCaseImplTest {
	private val pagingSource = mockk<CharacterPagingSource>()
	private val useCase = GetCharactersListUseCaseImpl()

	private val character = Character(
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


	@Test
	fun `getCharactersList should return a list of characters`() = runTest {
		val characters = listOf(character)
		coEvery { pagingSource.load(any()) } returns getPage(characters)
		val result = useCase.getCharactersList()

		result.map { pagingData ->
			pagingData.map {
				assertEquals(characters, it)
			}
		}
	}


	@Test
	fun `getCharactersList returns characters when name is null`() = runTest {
		val characters = emptyList<Character>()
		coEvery { pagingSource.load(any()) } returns getPage(characters)
		val result = useCase.getCharactersList().first()
		result.map { characterList ->
			characterList.map {
				assertEquals(character, it)
			}
		}
	}

	@Test
	fun `getCharactersList returns characters when name is not null`() = runTest {
		val characters = listOf(character)
		coEvery { pagingSource.load(any()) } returns getPage(characters)
		val result = useCase.getCharactersList(character.name).first()
		result.map { characterList ->
			characterList.map {
				assertEquals(character, it)
			}
		}
	}

	private fun getPage(characters: List<Character>): PagingSource.LoadResult.Page<Int, Character> =
		PagingSource.LoadResult.Page(
			data = characters,
			prevKey = null,
			nextKey = null,
		)
}
