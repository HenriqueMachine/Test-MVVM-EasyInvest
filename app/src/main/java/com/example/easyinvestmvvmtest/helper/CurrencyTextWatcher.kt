package com.example.easyinvestmvvmtest.helper

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import com.example.easyinvestmvvmtest.R
import com.example.easyinvestmvvmtest.helper.extension.onlyNumbers
import com.example.easyinvestmvvmtest.helper.extension.removeCurrencySymbol
import java.text.NumberFormat

class CurrencyTextWatcher(
    private var _editText: AppCompatEditText,
    private var context: Context
) : TextWatcher {
    private var _current = ""
    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if(s.toString() != _current){
            _editText.removeTextChangedListener(this)

            val cleanString = s.toString().onlyNumbers()
            val parsed = cleanString.toDouble()
            var formatted = NumberFormat.getCurrencyInstance().format(parsed/100)
            formatted = formatted.removeCurrencySymbol()
            _current = formatted
            _editText.setText(context.getString(R.string.text_value, formatted))
            _editText.setSelection(formatted.length + 3)
            _editText.addTextChangedListener(this)
        }

    }

}