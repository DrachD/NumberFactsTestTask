package com.example.data.datasources.api

import android.util.Log
import com.example.common.GeneralResponse
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

open class BaseDataSource {

    protected suspend fun <T> wrapRetrofitException(block: suspend () -> Response<T>): GeneralResponse<T> {
        return try {
            val response = block()
            GeneralResponse.Success(response.body())
        } catch (e: HttpException) {
            fetchJsonMessage(e) { message, code ->
                GeneralResponse.Error(message, code)
            }
        } catch (e: IOException) {
            GeneralResponse.Error("Server connection error!", null)
        } catch (e: Exception) {
            Log.d("logs", e.message.toString())
            Log.d("logs", e.toString())
            GeneralResponse.Error(
                "An unexpected error has occurred! Please try again later.",
                null
            )
        }
    }

    private inline fun <T> fetchJsonMessage(
        e: HttpException,
        message: (String?, Int?) -> GeneralResponse.Error<T>
    ): GeneralResponse.Error<T> {
        return try {
            val jsonObj = JSONObject(e.response()!!.errorBody()!!.charStream().readText())
            message.invoke(jsonObj.getString("message"), e.code())
        } catch (e: Exception) {
            message.invoke(e.message, null)
        }
    }
}