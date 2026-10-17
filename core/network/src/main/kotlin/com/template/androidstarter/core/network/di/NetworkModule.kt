// OPTIONAL — part of :core:network. Delete the whole module if the project does not use Retrofit
// (see "Removing an optional module" in SETUP.md).
package com.template.androidstarter.core.network.di

import com.template.androidstarter.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Hilt module providing the JSON parser, the HTTP client, and the [Retrofit] instance. Add one
 * `@Provides` function per API interface, for example:
 *
 * ```
 * @Provides
 * @Singleton
 * fun provideProfileApi(retrofit: Retrofit): ProfileApi = retrofit.create(ProfileApi::class.java)
 * ```
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 30L

    /** Provides the JSON configuration; unknown fields are ignored so new backend fields do not break parsing. */
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    /** Provides the OkHttp client; request and response bodies are logged in debug builds only. */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }.apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            redactHeader("Authorization")
            redactHeader("Cookie")
        }
        Timber.d("Creating OkHttpClient: timeout=%ds, logging=%s", TIMEOUT_SECONDS, logging.level)
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    /** Provides the [Retrofit] instance pointing at `BuildConfig.BASE_URL`. */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        Timber.d("Creating Retrofit: baseUrl=%s", BuildConfig.BASE_URL)
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}
