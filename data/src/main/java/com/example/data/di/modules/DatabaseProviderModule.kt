package com.example.data.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.datasources.db.NumberFactsDao
import com.example.data.datasources.db.NumberFactsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseProviderModule {

    @Singleton
    @Provides
    fun factsDatabase(context: Context): NumberFactsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NumberFactsDatabase::class.java,
            "number_facts_db"
        ).build()
    }

    @Singleton
    @Provides
    fun factsDao(database: NumberFactsDatabase): NumberFactsDao {
        return database.numberFactsDao()
    }
}