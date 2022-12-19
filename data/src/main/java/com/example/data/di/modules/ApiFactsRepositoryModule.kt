package com.example.data.di.modules

import com.example.data.ApiFactsRepositoryImpl
import com.example.domain.ApiFactsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ApiFactsRepositoryModule {

    @Binds
    abstract fun provideApiFactsRepository(apiFactsRepository: ApiFactsRepositoryImpl): ApiFactsRepository
}