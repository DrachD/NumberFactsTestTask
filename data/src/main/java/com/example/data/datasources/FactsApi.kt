package com.example.data.datasources

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FactsApi {

    @GET("/{id}")
    suspend fun fetchFactsByNumber(@Path("id") number: Int): Response<String>

    @GET("/random/math")
    suspend fun fetchFactsByRandomNumber(): Response<String>
}