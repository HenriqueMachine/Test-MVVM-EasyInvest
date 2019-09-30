package com.example.easyinvestmvvmtest.helper.extension

import java.text.NumberFormat
import java.util.*

fun maskFormatterCurrency(value: Double?): String {
    val COUNTRY = "BR"
    val LANGUAGE = "pt"
    return NumberFormat.getCurrencyInstance(Locale(LANGUAGE, COUNTRY)).format(value)
}

fun String.currencyToDouble(): Double {
    return try {
        val value = this.onlyNumbers()
        value.toDouble() / 100
    } catch (e: Exception) {
        0.0
    }

}

fun String.onlyNumbers(): String {
    return this.replace("[^0-9]".toRegex(), "")
        .trim()
}

fun getDate(date:String):String{
    val dateToFormat = date.split("/")
    val year =    dateToFormat[2]
    val month = dateToFormat[1]
    val day = dateToFormat[0]

    return "$year-$month-$day"
}

fun String.removeCurrencySymbol(): String {
    return this.replace("[a-zA-Z\\s\\$]+".toRegex(), "")
        .trim()
}