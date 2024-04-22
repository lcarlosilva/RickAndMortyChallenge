package br.com.luiz.commons.di

import br.com.luiz.commons.network.provideConverterFactory
import br.com.luiz.commons.network.provideLoggingInterceptor
import br.com.luiz.commons.network.provideOkHttpClient
import br.com.luiz.commons.network.provideRetrofit
import br.com.luiz.commons.utils.BuildConfigProvider
import okhttp3.Interceptor
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

fun commonsModule(): Module {
    return module {
        factory { provideLoggingInterceptor(get<BuildConfigProvider>()) } bind Interceptor::class
        single { provideOkHttpClient(getAll()) }
        single { provideConverterFactory() }
        single { provideRetrofit(get<BuildConfigProvider>().urlBase, get(), get()) }
    }
}
