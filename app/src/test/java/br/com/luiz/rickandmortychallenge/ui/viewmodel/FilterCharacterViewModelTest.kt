package br.com.luiz.rickandmortychallenge.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import br.com.luiz.domain.mappers.toCharacterList
import br.com.luiz.domain.model.Character
import br.com.luiz.domain.usecase.GetCharactersListUseCase
import br.com.luiz.rickandmortychallenge.ui.view.state.CharacterListState
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
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class FilterCharacterViewModelTest {

	@get:Rule
	var rule: TestRule = InstantTaskExecutorRule()

	@get:Rule
	val coroutineTestRule = CoroutineTestRule()

	private val getCharactersListUseCase: GetCharactersListUseCase = mockk(relaxed = true)
	private lateinit var viewModel: FilterCharacterViewModel

	@Test
	fun `searchCharacterByName updates searchResult when getCharactersList is successful`() =
		runTest {
			val data = listOf(characterMock().toCharacterList())
			val characters = PagingData.from(data)
			val dataList = flowOf(characters)
			coEvery { getCharactersListUseCase.getCharactersList(anyString()) } returns dataList

			viewModel = FilterCharacterViewModel(getCharactersListUseCase)
			viewModel.searchCharacterByName("Rick")

			viewModel.searchResult.value.run {
				this.dataList?.let { pagingDataFlow ->
					pagingDataFlow.collect { pagingData ->
						assertEquals(data, pagingData)
						pagingData.map { characters ->
							assertEquals(data.first(), characters)
						}
					}
				}
			}
		}

	@Test
	fun `searchCharacterByName updates searchResult to empty when getCharactersList is unsuccessful`() =
		runTest {
			val characters = PagingData.empty<List<Character>>()
			coEvery { getCharactersListUseCase.getCharactersList(anyString()) } returns flowOf(
				characters
			)

			viewModel = FilterCharacterViewModel(getCharactersListUseCase)
			viewModel.searchCharacterByName("Unknown")

			viewModel.searchResult.value.run {
				assertEquals(CharacterListState(dataList = null), this)
			}
		}

	@Test
	fun `searchCharacter updates searchString`() {
		viewModel = FilterCharacterViewModel(getCharactersListUseCase)
		viewModel.searchCharacter("Rick")

		assertEquals("Rick", viewModel.searchString.value)
	}

	@Test
	fun `searchCharacter sets searchResult to empty when name is empty`() {
		viewModel = FilterCharacterViewModel(getCharactersListUseCase)
		viewModel.searchCharacter("")

		assertEquals(CharacterListState(dataList = null), viewModel.searchResult.value)
	}
}
