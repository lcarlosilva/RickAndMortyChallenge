package br.com.luiz.rickandmortychallenge.ui.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import br.com.luiz.domain.mappers.toCharacterList
import br.com.luiz.domain.model.Character
import br.com.luiz.domain.usecase.GetCharactersListUseCase
import br.com.luiz.rickandmortychallenge.utils.CoroutineTestRule
import br.com.luiz.rickandmortychallenge.utils.characterMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharactersListViewModelTest {

	@get:Rule
	var rule: TestRule = InstantTaskExecutorRule()

	@get:Rule
	val coroutineTestRule = CoroutineTestRule()

	private val getCharactersListUseCase: GetCharactersListUseCase = mockk(relaxed = true)
	private lateinit var viewModel: CharactersListViewModel

	@Test
	fun `listCharacterState emits characters when getCharactersList is successful`() = runTest {
		val data = listOf(characterMock().toCharacterList())
		val characters = PagingData.from(data)
		coEvery { getCharactersListUseCase.getCharactersList() } returns flowOf(characters)

		viewModel = CharactersListViewModel(getCharactersListUseCase)

		viewModel.listCharacterState.value.run {
			characters.map {
				this.map { character ->
					assertEquals(it[0].name, character[0].name)
				}
			}
		}
	}

	@Test
	fun `listCharacterState emits empty when getCharactersList is unsuccessful`() = runTest {
		val characters = PagingData.empty<List<Character>>()
		coEvery { getCharactersListUseCase.getCharactersList() } returns flowOf(characters)

		viewModel = CharactersListViewModel(getCharactersListUseCase)

		viewModel.listCharacterState.value.run {
			characters.map {
				this.map { character ->
					assertEquals(it[0].name, character[0].name)
				}
			}
		}
	}
}