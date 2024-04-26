package br.com.luiz.domain.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.luiz.commons.utils.extensions.getPageFromUrl
import br.com.luiz.domain.model.Character
import br.com.luiz.domain.repository.RickAndMortyRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private const val STARTING_PAGE_INDEX = 1

private const val PAGE = "page"

class CharacterPagingSource(
    private val name: String? = null
) : KoinComponent, PagingSource<Int, Character>() {
    private val repository: RickAndMortyRepository by inject()

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPage = params.key ?: STARTING_PAGE_INDEX
            val response = repository.getCharactersList(nextPage, name)
            LoadResult.Page(
                data = response.results,
                prevKey = response.prev.getPageFromUrl(PAGE),
                nextKey = response.next.getPageFromUrl(PAGE),
            )
        } catch (e: Exception) {
            Log.d("CharacterPagingSource", "load: ${e.message}")
            LoadResult.Error(e)
        }
    }
}
