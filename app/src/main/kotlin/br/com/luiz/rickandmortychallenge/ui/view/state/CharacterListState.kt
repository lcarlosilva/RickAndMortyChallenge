package br.com.luiz.rickandmortychallenge.ui.view.state

import androidx.paging.PagingData
import br.com.luiz.commons.utils.extensions.emptyString
import br.com.luiz.domain.model.Character
import kotlinx.coroutines.flow.Flow

data class CharacterListState(
	var isLoading: Boolean = false,
	val dataList: Flow<PagingData<List<Character>>>? = null,
	val errorMessage: String = emptyString()
)