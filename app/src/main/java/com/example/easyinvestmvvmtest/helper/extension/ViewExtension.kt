package com.example.easyinvestmvvmtest.helper.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.example.easyinvestmvvmtest.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun maskFormatterCurrency(value: Double?): String {
    val country = "BR"
    val language = "pt"
    return NumberFormat.getCurrencyInstance(Locale(language, country)).format(value)
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

fun Double.toPercent(): String {
    val percent = this.toString()
    return "$percent%"
}

fun AppCompatEditText.listenerChange(callbackTextChange: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            callbackTextChange.invoke(p0.toString())
        }

    })
}

fun getDate(date: String): String {
    val dateToFormat = date.split("/")
    val year = dateToFormat[2]
    val month = dateToFormat[1]
    val day = dateToFormat[0]

    return "$year-$month-$day"
}

fun String.formatToDate(locale: Locale): String? {
    val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale)
    val finalFormat = SimpleDateFormat("dd/MM/yyyy", locale)
    val date = originalFormat.parse(this)
    date.let {
        return finalFormat.format(it)
    }
}

fun String.removeCurrencySymbol(): String {
    return this.replace("[a-zA-Z\\s$]+".toRegex(), "")
        .trim()
}

fun View.setVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun AppCompatEditText.currencyWatcher(editText: AppCompatEditText) {
    var current = ""
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0.toString() != current) {
                editText.removeTextChangedListener(this)

                val cleanString = p0.toString().onlyNumbers()
                if (cleanString.isNotEmpty()){
                    val parsed = cleanString.toDouble()
                    var formatted = NumberFormat.getCurrencyInstance().format(parsed / 100)
                    formatted = formatted.removeCurrencySymbol()
                    current = formatted
                    editText.setText(context.getString(R.string.text_value, formatted))
                    editText.setSelection(formatted.length + 3)
                    editText.addTextChangedListener(this)
                }
            }
        }

    })

}

fun AppCompatEditText.percentWatcher(editText: AppCompatEditText) {
    var current = ""
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0.toString() != current) {
                editText.removeTextChangedListener(this)

                val cleanString = p0.toString().onlyNumbers()
                if (cleanString.isNotEmpty()) {
                    val parsed = cleanString.toInt()
                    current = parsed.toString()
                    editText.setText(context.getString(R.string.text_percent, parsed.toString()))
                    editText.setSelection(parsed.toString().length)
                }

                editText.addTextChangedListener(this)
            }
        }
    })

}