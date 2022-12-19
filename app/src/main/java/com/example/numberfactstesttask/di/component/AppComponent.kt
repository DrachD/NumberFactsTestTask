package com.example.numberfactstesttask.di.component

import android.content.Context
import com.example.data.di.modules.*
import com.example.numberfactstesttask.screens.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiProviderModule::class,
    DatabaseProviderModule::class,
    ApiDataSourceModule::class,
    ApiFactsRepositoryModule::class,
    DatabaseFactsRepositoryModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: HomeFragment)
}