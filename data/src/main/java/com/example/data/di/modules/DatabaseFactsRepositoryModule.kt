package com.example.data.di.modules

import com.example.data.DatabaseFactsRepositoryImpl
import com.example.domain.DatabaseFactsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DatabaseFactsRepositoryModule {

    @Binds
    abstract fun provideDatabaseFactsRepository(databaseFactsRepository: DatabaseFactsRepositoryImpl): DatabaseFactsRepository
}