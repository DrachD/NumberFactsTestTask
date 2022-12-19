package com.example.common.util

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

fun <T : Parcelable?> Bundle.getBaseParcelable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, clazz)
    } else {
        getParcelable<T>(key)
    }
}