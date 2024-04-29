package br.com.luiz.data.api

import br.com.luiz.data.model.ApiResponse
import br.com.luiz.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {
	@GET("character")
	suspend fun getCharactersList(
		@Query("page") page: Int,
		@Query("name") name: String? = null,
		@Query("status") status: String? = null
	): ApiResponse<CharacterResponse>
}
