package com.example.domain

import androidx.lifecycle.LiveData
import com.example.common.model.NumberFactsData

interface DatabaseFactsRepository {
    suspend fun addNumberFacts(numberFactsData: NumberFactsData)
    fun getAllNumberFacts(): LiveData<List<NumberFactsData>>
    fun getFactsByNumber(number: Int): LiveData<NumberFactsData>
}