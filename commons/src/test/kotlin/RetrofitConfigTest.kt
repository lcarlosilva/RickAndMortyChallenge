package br.com.luiz.commons.network

import br.com.luiz.commons.utils.BuildConfigProvider
import io.mockk.every
import io.mockk.mockk
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Converter

class RetrofitConfigTest {
    private val config = mockk<BuildConfigProvider>()
    private val client = mockk<OkHttpClient>()
    private val converter = mockk<Converter.Factory>()

    @Test
    fun `test provideLoggingInterceptor`() {
        // Arrange
        every { config.isDebug } returns true

        // Act
        val interceptor = provideLoggingInterceptor(config)

        // Assert
        assertEquals(
            HttpLoggingInterceptor.Level.BODY,
            (interceptor as HttpLoggingInterceptor).level,
        )
    }

    @Test
    fun `test provideOkHttpClient`() {
        // Arrange
        val interceptors = listOf<Interceptor>(mockk())

        // Act
        val okHttpClient = provideOkHttpClient(interceptors)

        // Assert
        assertEquals(interceptors.size, okHttpClient.interceptors.size)
    }

    @Test
    fun `test provideConverterFactory`() {
        // Act
        val converterFactory = provideConverterFactory()

        // Assert
        assertTrue(converterFactory is Converter.Factory)
    }

    @Test
    fun `test provideRetrofit`() {
        // Arrange
        val baseUrl = "http://test.com/"

        // Act
        val retrofit = provideRetrofit(baseUrl, client, converter)

        // Assert
        assertEquals(baseUrl, retrofit.baseUrl().toString())
    }
}
