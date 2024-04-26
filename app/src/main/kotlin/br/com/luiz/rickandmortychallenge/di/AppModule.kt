package br.com.luiz.rickandmortychallenge.di

import br.com.luiz.commons.utils.BuildConfigProvider
import br.com.luiz.rickandmortychallenge.ui.viewmodel.CharactersListViewModel
import br.com.luiz.rickandmortychallenge.ui.viewmodel.FilterCharacterViewModel
import br.com.luiz.rickandmortychallenge.utils.AppBuildConfigProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun appModule(): Module {
    return module {
        single<BuildConfigProvider> { AppBuildConfigProvider() }

        viewModel { CharactersListViewModel(get()) }
        viewModel { FilterCharacterViewModel(get()) }
    }
}
