package com.example.numberfactstesttask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.ApiFactsRepository
import com.example.domain.DatabaseFactsRepository
import com.example.numberfactstesttask.screens.home.HomeViewModel
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val apiFactsRepository: ApiFactsRepository,
    private val databaseFactsRepository: DatabaseFactsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(apiFactsRepository, databaseFactsRepository) as T
    }
}