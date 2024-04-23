package br.com.luiz.domain.repository

import br.com.luiz.domain.model.Character
import br.com.luiz.domain.model.Response

interface RickAndMortyRepository {
    suspend fun getCharactersList(page: Int): Response<Character>
}
