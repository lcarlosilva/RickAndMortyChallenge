package br.com.luiz.domain.di

import br.com.luiz.domain.usecase.GetCharactersListUseCase
import br.com.luiz.domain.usecase.GetCharactersListUseCaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module

fun domainModule(): Module {
    return module {
        factory<GetCharactersListUseCase> { GetCharactersListUseCaseImpl() }
    }
}
