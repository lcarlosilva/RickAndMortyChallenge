package br.com.luiz.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import br.com.luiz.domain.mappers.toCharacterList
import br.com.luiz.domain.model.Character
import br.com.luiz.domain.pagingSource.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PAGE_SIZE_DEFAULT = 10

interface GetCharactersListUseCase {
    suspend fun getCharactersList(): Flow<PagingData<List<Character>>>
}

class GetCharactersListUseCaseImpl : GetCharactersListUseCase {
    override suspend fun getCharactersList(): Flow<PagingData<List<Character>>> =
        Pager(config = PagingConfig(pageSize = PAGE_SIZE_DEFAULT)) { CharacterPagingSource() }
            .flow
            .map { value: PagingData<Character> ->
                value.map { character: Character ->
                    character.toCharacterList()
                }
            }
}
