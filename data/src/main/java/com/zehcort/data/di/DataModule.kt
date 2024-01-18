package com.zehcort.data.di

import com.zehcort.data.apis.uf.Constants
import com.zehcort.data.apis.uf.FactsApi
import com.zehcort.data.repositories.FactsRepositoryImpl
import com.zehcort.domain.repositories.FactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideUfFactsApi(): FactsApi {
        return Retrofit.Builder().baseUrl(Constants.UF_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(FactsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFactsRepository(factsApi: FactsApi): FactsRepository {
        return FactsRepositoryImpl(factsApi)
    }
}