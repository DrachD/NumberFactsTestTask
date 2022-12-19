package com.example.domain

import com.example.common.GeneralResponse
import com.example.common.model.NumberData

interface ApiFactsRepository {
    suspend fun fetchFactsByNumber(numberData: NumberData): GeneralResponse<String>
    suspend fun fetchFactsByRandomNumber(): GeneralResponse<String>
}