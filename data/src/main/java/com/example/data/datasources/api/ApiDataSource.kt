package com.example.data.datasources.api

import com.example.common.GeneralResponse

interface ApiDataSource {

    suspend fun fetchFactsByNumber(number: Int): GeneralResponse<String>
    suspend fun fetchFactsByRandomNumber(): GeneralResponse<String>
}