package com.example.common.model

import com.example.common.util.EmptyFieldException
import com.example.common.util.NotNumberException
import java.text.NumberFormat
import java.text.ParsePosition

data class NumberData(
    val number: String
) {
    fun validate() {
        if (number.isBlank()) throw EmptyFieldException()
        if (!inNumeric(number)) throw NotNumberException()
    }

    private fun inNumeric(s: String): Boolean {
        val pos = ParsePosition(0)
        NumberFormat.getInstance().parse(s, pos)
        return s.length == pos.index
    }
}
