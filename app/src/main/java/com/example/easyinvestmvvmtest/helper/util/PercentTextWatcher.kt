package com.example.easyinvestmvvmtest.helper.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import com.example.easyinvestmvvmtest.R
import com.example.easyinvestmvvmtest.helper.extension.onlyNumbers

class PercentTextWatcher(
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
            if (cleanString.isNotEmpty()){
                val parsed = cleanString.toInt()
                _current = parsed.toString()
                _editText.setText(context.getString(R.string.text_percent, parsed.toString()))
                _editText.setSelection(parsed.toString().length)
            }

            _editText.addTextChangedListener(this)
        }

    }

}