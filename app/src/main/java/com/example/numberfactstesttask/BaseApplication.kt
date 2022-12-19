package com.example.numberfactstesttask

import android.app.Application
import com.example.numberfactstesttask.di.component.AppComponent
import com.example.numberfactstesttask.di.component.DaggerAppComponent

class BaseApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(this)
    }
}