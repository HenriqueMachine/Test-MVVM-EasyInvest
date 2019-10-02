package com.example.easyinvestmvvmtest.helper.extension

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
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

fun Double.toPercent() : String {
    val percent = this.toString()
    return "$percent%"
}

fun AppCompatEditText.listenerChange(callbackTextChange: (String) -> Unit){
    this.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            callbackTextChange.invoke(p0.toString())
        }

    })
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