package br.com.luiz.data.repository

import android.util.Log
import br.com.luiz.data.api.RickAndMortyService
import br.com.luiz.data.mapper.toEntity
import br.com.luiz.domain.model.Character
import br.com.luiz.domain.model.Response
import br.com.luiz.domain.repository.RickAndMortyRepository

class RickAndMortyRepositoryImpl(
    private val service: RickAndMortyService,
) : RickAndMortyRepository {
    override suspend fun getCharactersList(page: Int): Response<Character> {
        return try {
            service.getCharactersList(page).toEntity {
                Character(
                    id = it.id,
                    name = it.name,
                    status = it.status,
                    species = it.species,
                    type = it.type,
                    gender = it.gender,
                    origin = it.origin.toEntity(),
                    location = it.location.toEntity(),
                    image = it.image,
                )
            }
        } catch (e: Exception) {
            Log.d("RickAndMortyRepository", "getCharactersList: ${e.message}")
            Response(0, 0, null, null, emptyList())
        }
    }
}
