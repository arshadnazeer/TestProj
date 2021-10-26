package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.data.api.FMClientService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(private val baseUrl : String) {
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideFMClientService(retrofit: Retrofit) : FMClientService{
        return retrofit.create(FMClientService::class.java)
    }
}