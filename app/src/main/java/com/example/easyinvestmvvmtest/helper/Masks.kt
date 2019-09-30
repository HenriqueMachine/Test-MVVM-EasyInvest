package com.example.easyinvestmvvmtest.helper

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText

object Masks {

    const val DATE_MASK = "##/##/####"
    //Percent
    const val PERCENT_MASK_1 = "#%"
    const val PERCENT_MASK_2 = "##%"
    const val PERCENT_MASK_3 = "###%"
    //Currency
    const val CURRENCY_MASK_0 = "R$ #,##"
    const val CURRENCY_MASK_1 = "R$ ##,##"
    const val CURRENCY_MASK_2 = "R$ ###,##"
    const val CURRENCY_MASK_3 = "R$ #.##,##"
    const val CURRENCY_MASK_4 = "R$ ##.##,##"
    const val CURRENCY_MASK_5 = "R$ ###.##,##"
    const val CURRENCY_MASK_6 = "R$ #.###.##,##"
    const val CURRENCY_MASK_7 = "R$ ##.###.##,##"
    const val CURRENCY_MASK_8 = "R$ ###.###.##,##"

    fun applyMask(s: CharSequence, mask: String): String {

        val str = unmask(s.toString())
        var maskApply = ""

        var i = 0
        for (m in mask.toCharArray()) {
            if (m != '#') {
                maskApply += m
                continue
            }
            try {
                maskApply += str[i]
            } catch (e: Exception) {
                break
            }

            i++
        }

        return maskApply
    }

    fun mask(ediTxt: AppCompatEditText, maskFormatter: String): TextWatcher {
        return object : TextWatcher {
            var isUpdating: Boolean = false
            var old = ""
            var isDelete = false

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                isDelete = count > after
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                val str = unmask(s.toString())
                var maskApply = ""

                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }

                if (isDelete) {

                    maskApply = s.toString()
                    if (maskApply.isNotEmpty()) {

                        try {

                            if (maskFormatter.contains(maskApply[maskApply.length - 1])) {
                                maskApply = maskApply.substring(0, maskApply.length - 1)
                            }

                        } catch (e: Exception) {

                        }

                    }

                } else {

                    var i = 0
                    for (m in maskFormatter.toCharArray()) {
                        if (m != '#' && str.length != old.length) {
                            maskApply += m
                            continue
                        }
                        try {
                            maskApply += str[i]
                        } catch (e: Exception) {
                            break
                        }

                        i++
                    }

                }

                isUpdating = true

                ediTxt.post {

                    try {
                        ediTxt.setText(maskApply)
                        ediTxt.setSelection(maskApply.length)
                    } catch (e: Exception) {
                    }

                }

            }
        }
    }

    private fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
            .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "").replace("[ ]".toRegex(), "")
            .replace("[)]".toRegex(), "")
    }
}