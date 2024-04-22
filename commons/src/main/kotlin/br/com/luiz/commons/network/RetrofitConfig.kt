package br.com.luiz.commons.network

import br.com.luiz.commons.utils.BuildConfigProvider
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val TIMEOUT_VALUE = 30L

fun provideLoggingInterceptor(config: BuildConfigProvider) =
    HttpLoggingInterceptor().apply {
        if (config.isDebug) {
            this.level = HttpLoggingInterceptor.Level.BODY
        } else {
            this.level = HttpLoggingInterceptor.Level.NONE
        }
    }

fun provideOkHttpClient(interceptors: List<Interceptor>): OkHttpClient {
    return OkHttpClient.Builder().run {
        readTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
        connectTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
        interceptors.forEach(::addInterceptor)
        build()
    }
}

fun provideConverterFactory(): Converter.Factory {
    val contentType = "application/json".toMediaType()
    val json =
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    return json.asConverterFactory(contentType)
}

fun provideRetrofit(
    baseUrl: String,
    client: OkHttpClient,
    converter: Converter.Factory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(converter)
        .build()
}
