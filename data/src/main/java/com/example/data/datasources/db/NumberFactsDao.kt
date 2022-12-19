package com.example.data.datasources.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.common.model.NumberFactsData

@Dao
interface NumberFactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNumberFacts(numberFactsData: NumberFactsData)

    @Query("SELECT * FROM numberFacts ORDER BY id DESC")
    fun getAllNumberFacts(): LiveData<List<NumberFactsData>>

    @Query("SELECT * FROM numberFacts WHERE number=(:number)")
    fun getFactsByNumber(number: Int): LiveData<NumberFactsData>
}