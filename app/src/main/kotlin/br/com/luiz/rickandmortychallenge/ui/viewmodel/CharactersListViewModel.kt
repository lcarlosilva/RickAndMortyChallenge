package br.com.luiz.rickandmortychallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.luiz.domain.model.Character
import br.com.luiz.domain.usecase.GetCharactersListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersListViewModel(
    private val getCharactersListUseCase: GetCharactersListUseCase,
) : ViewModel() {
    private val _listCharacterState: MutableStateFlow<PagingData<List<Character>>> =
        MutableStateFlow(PagingData.empty())
    val listCharacterState = _listCharacterState.asStateFlow()

    init {
        viewModelScope.launch {
            getCharactersList()
        }
    }

    private suspend fun getCharactersList() {
        viewModelScope.launch {
            getCharactersListUseCase.getCharactersList().cachedIn(viewModelScope).collect {
                _listCharacterState.value = it
            }
        }
    }
}
