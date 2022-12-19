package com.example.common.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numberFacts")
data class NumberFactsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val number: Int,
    val fact: String
)