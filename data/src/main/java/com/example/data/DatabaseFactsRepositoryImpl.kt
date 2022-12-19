package com.example.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.common.model.NumberFactsData
import com.example.data.di.modules.DatabaseProviderModule
import com.example.domain.DatabaseFactsRepository
import javax.inject.Inject

class DatabaseFactsRepositoryImpl @Inject constructor(
    context: Context
) : DatabaseFactsRepository {

    private val database = DatabaseProviderModule().factsDatabase(context)
    private val dao = DatabaseProviderModule().factsDao(database)

    override suspend fun addNumberFacts(numberFactsData: NumberFactsData) {
        dao.addNumberFacts(numberFactsData)
    }

    override fun getAllNumberFacts(): LiveData<List<NumberFactsData>> = dao.getAllNumberFacts()

    override fun getFactsByNumber(number: Int): LiveData<NumberFactsData> {
        return dao.getFactsByNumber(number)
    }
}