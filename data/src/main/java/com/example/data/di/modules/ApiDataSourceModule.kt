package com.example.data.di.modules

import com.example.data.datasources.api.ApiDataSource
import com.example.data.datasources.api.ApiDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ApiDataSourceModule {

    @Binds
    abstract fun provideApiDataSource(apiDataSource: ApiDataSourceImpl): ApiDataSource
}