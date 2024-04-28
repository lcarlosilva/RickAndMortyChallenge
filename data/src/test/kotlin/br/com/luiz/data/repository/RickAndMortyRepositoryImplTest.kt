package br.com.luiz.data.repository


import br.com.luiz.data.api.RickAndMortyService
import br.com.luiz.data.model.ApiResponse
import br.com.luiz.data.model.CharacterResponse
import br.com.luiz.data.model.LocationResponse
import br.com.luiz.data.model.OriginResponse
import br.com.luiz.domain.mappers.toCharacterList
import br.com.luiz.domain.model.Character
import br.com.luiz.domain.model.Response
import br.com.luiz.domain.repository.RickAndMortyRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class RickAndMortyRepositoryImplTest {

	private var service = mockk<RickAndMortyService>(relaxed = true)
	private lateinit var repository: RickAndMortyRepository

	@Before
	fun setUp() {
		repository = RickAndMortyRepositoryImpl(service)
	}

	@Test
	fun `getCharactersList returns characters when service call is successful`() = runTest {
		val expectedResponse = ApiResponse(
			ApiResponse.Info(
				count = 1,
				pages = 1,
				next = null,
				prev = null,
			),
			listOf(
				CharacterResponse(
					id = 1,
					name = "Rick",
					status = "Alive",
					species = "Human",
					type = "Scientist",
					gender = "male",
					origin = OriginResponse("Earth"),
					location = LocationResponse("Earth"),
					image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
				)
			)
		)
		coEvery { service.getCharactersList(any(), any()) } returns expectedResponse

		val actualResponse = repository.getCharactersList(1, null)

		assertNotNull(actualResponse)

		actualResponse.results.first().toCharacterList().toList().let {
			assertEquals(expectedResponse.results.first().id, it.first().id)
			assertEquals(expectedResponse.results.first().name, it.first().name)
			assertEquals(expectedResponse.results.first().status, it.first().status)
			assertEquals(expectedResponse.results.first().species, it.first().species)
			assertEquals(expectedResponse.results.first().type, it.first().type)
			assertEquals(expectedResponse.results.first().location.name, it.first().location.name)
			assertEquals(expectedResponse.results.first().origin.name, it.first().origin.name)
			assertEquals(expectedResponse.results.first().image, it.first().image)

		}
	}

	@Test
	fun `getCharactersList returns empty response when service call fails`() = runTest {
		val expectedResponse = Response<Character>(0, 0, null, null, emptyList())

		coEvery { service.getCharactersList(any(), any()) } throws Exception()

		val actualResponse = repository.getCharactersList(1, null)

		assertNotNull(actualResponse)

		assertEquals(expectedResponse, actualResponse)
	}
}