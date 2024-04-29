package br.com.luiz.rickandmortychallenge.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.luiz.commons.utils.extensions.emptyString
import br.com.luiz.domain.usecase.GetCharactersListUseCase
import br.com.luiz.rickandmortychallenge.ui.view.state.CharacterListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class FilterCharacterViewModel(
	private val getCharactersListUseCase: GetCharactersListUseCase,
) : ViewModel() {

	private var _searchResult = mutableStateOf(CharacterListState())
	var searchResult = _searchResult

	private val _searchString = MutableStateFlow(emptyString())
	val searchString = _searchString.asStateFlow()

	private var searchJob: Job? = null

	fun searchCharacterByName(searchString: String? = null, status: String? = null) {
		searchJob?.cancel()
		searchJob = viewModelScope.launch {
			if (searchString != null) {
				if (searchString.length > 3) delay(500)
			}
			val response = getCharactersListUseCase.getCharactersList(searchString, status)
			_searchResult.value = CharacterListState(dataList = response)
		}
	}

	fun searchCharacter(name: String) {
		if (name == emptyString()) {
			_searchResult.value = CharacterListState(
				dataList = null
			)
		}
		_searchString.value = name
	}
}