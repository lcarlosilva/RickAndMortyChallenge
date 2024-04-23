package br.com.luiz.domain.usecase

import androidx.paging.PagingData
import br.com.luiz.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface GetCharactersListUseCase {
    suspend fun getCharactersList(): Flow<PagingData<List<Character>>>
}

class GetCharactersListUseCaseImpl : GetCharactersListUseCase {
    override suspend fun getCharactersList(): Flow<PagingData<List<Character>>> {
        TODO("Not yet implemented")
    }
}
