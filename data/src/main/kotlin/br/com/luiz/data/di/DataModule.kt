package br.com.luiz.data.di

import br.com.luiz.data.api.RickAndMortyService
import br.com.luiz.data.repository.RickAndMortyRepositoryImpl
import br.com.luiz.domain.repository.RickAndMortyRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun dataModule(): Module {
    return module {
        factory<RickAndMortyRepository> { RickAndMortyRepositoryImpl(get()) }
        factory<RickAndMortyService> { get<Retrofit>().create(RickAndMortyService::class.java) }
    }
}
