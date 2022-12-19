package com.example.data

import com.example.common.GeneralResponse
import com.example.common.model.NumberData
import com.example.data.datasources.api.ApiDataSource
import com.example.domain.ApiFactsRepository
import javax.inject.Inject

class ApiFactsRepositoryImpl @Inject constructor(
    private val apiDataSource: ApiDataSource
) : ApiFactsRepository {

    override suspend fun fetchFactsByNumber(numberData: NumberData): GeneralResponse<String> {
        numberData.validate()
        val number = numberData.number.toInt()

        return apiDataSource.fetchFactsByNumber(number)
    }

    override suspend fun fetchFactsByRandomNumber(): GeneralResponse<String> {
        return apiDataSource.fetchFactsByRandomNumber()
    }
}