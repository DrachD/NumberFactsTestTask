package com.example.data.datasources.api

import com.example.data.datasources.FactsApi
import retrofit2.Retrofit
import javax.inject.Inject


class ApiDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit,
    private val retrofitApi: FactsApi
) : ApiDataSource, BaseDataSource() {

    override suspend fun fetchFactsByNumber(number: Int) = wrapRetrofitException {
        retrofitApi.fetchFactsByNumber(number)
    }

    override suspend fun fetchFactsByRandomNumber() = wrapRetrofitException {
        retrofitApi.fetchFactsByRandomNumber()
    }
}