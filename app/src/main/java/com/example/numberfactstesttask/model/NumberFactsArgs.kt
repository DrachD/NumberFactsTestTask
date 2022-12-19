package com.example.numberfactstesttask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NumberFactsArgs(
    val number: Int,
    val fact: String
): Parcelable