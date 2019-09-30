package com.example.easyinvestmvvmtest.helper.extension

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import java.text.NumberFormat
import java.util.*

fun Double.convertDoubleToMoneyWithPrefiString(prefix: String): String {
    val value = NumberFormat.getCurrencyInstance().format(this).removeCurrencySymbol()
    return "$prefix $value"
}

fun String.removeCurrencySymbol(): String {
    return this.replace("[a-zA-Z\\s\\$]+".toRegex(), "")
        .trim()
}

fun Double.convertDoubleToMoneyString(locale: Locale = Locale("pt", "BR")): String {
    return NumberFormat.getCurrencyInstance(locale).format(this).replace("R$", "R$")
}

fun maskFormatterCurrency(value: Double?): String {
    val COUNTRY = "BR"
    val LANGUAGE = "pt"
    return NumberFormat.getCurrencyInstance(Locale(LANGUAGE, COUNTRY)).format(value)
}

fun AppCompatEditText.onTextChanged(callbackOnTextChange: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            callbackOnTextChange.invoke(p0.toString())
        }
    })
}