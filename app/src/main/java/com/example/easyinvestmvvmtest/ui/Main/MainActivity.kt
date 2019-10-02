package com.example.easyinvestmvvmtest.ui.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.R
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
