package com.example.data.datasources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.common.model.NumberFactsData

@Database(entities = [NumberFactsData::class], version = 1)
abstract class NumberFactsDatabase : RoomDatabase() {

    abstract fun numberFactsDao(): NumberFactsDao
}