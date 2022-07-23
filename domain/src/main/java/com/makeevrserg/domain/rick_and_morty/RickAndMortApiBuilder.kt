package com.makeevrserg.domain.rick_and_morty

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RickAndMortApiBuilder {


    private fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(10L, TimeUnit.SECONDS)
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .writeTimeout(10L, TimeUnit.SECONDS)
        .build()


    private fun build(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient())
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()

    private fun provideMainService(retrofit: Retrofit) = retrofit.create(RickAndMortAPI::class.java)
    private fun provideRetrofit(): Retrofit = build()
    val dataSource: RickAndMortyDataSource by lazy {
        val retrofit = provideRetrofit()
        val service = provideMainService(retrofit)
        RickAndMortyDataSource(service)
    }
}