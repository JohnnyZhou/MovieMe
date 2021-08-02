package com.johnnyzhou.movieme.di.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.johnnyzhou.movieme.data.MovieRepositoryImpl
import com.johnnyzhou.movieme.network.ApiInterceptor
import com.johnnyzhou.movieme.network.MovieService
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule(private val baseUrl: String) {
    @Provides
    @Singleton
    fun provideRestClient(movieService: MovieService?): MovieRepositoryImpl {
        return MovieRepositoryImpl(movieService!!)
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: Converter.Factory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
        }.asConverterFactory(MediaType.get("application/json"))
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val okHttpClient = OkHttpClient()
//        okHttpClient.interceptors().add(interceptor)
        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return ApiInterceptor()
    }
}