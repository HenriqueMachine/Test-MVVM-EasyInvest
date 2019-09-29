package com.example.easyinvestmvvmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: SimulateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val ft = supportFragmentManager.beginTransaction()
//        ft.replace(R.id.frame_layout_container, SimulateFragment())
//        ft.commit()
        viewModel.simulation(Investment(32323.0,"CDI",123,false,"2023-03-03"))
    }
}
