package br.com.luiz.domain.usecase

import android.annotation.SuppressLint
import android.util.Log
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
    suspend fun getCharactersList(name: String? = null, status: String? = null): Flow<PagingData<List<Character>>>
}

class GetCharactersListUseCaseImpl : GetCharactersListUseCase {
    @SuppressLint("LongLogTag")
    override suspend fun getCharactersList(name: String?, status: String?): Flow<PagingData<List<Character>>> =
        try {
            Pager(config = PagingConfig(pageSize = PAGE_SIZE_DEFAULT)) {
                CharacterPagingSource(name, status)
            }.flow.map { value: PagingData<Character> ->
                value.map { character: Character ->
                    character.toCharacterList()
                }
            }
        } catch (e: Exception) {
            Log.d("GetCharactersListUseCase", "getCharactersList: ${e.message}")
            throw e
        }
}
