package com.example.easyinvestmvvmtest.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.easyinvestmvvmtest.R
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class SimulateFragment : Fragment() {

    private val viewModel: SimulateViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simulate, container, false)

        subscribeUI()
        configureViews()

    }

    private fun configureViews() {
//        viewModel.simulation(Investment(32323.0,"CDI",123,false,"2023-03-03"))
    }

    private fun subscribeUI() {
        viewModel.calculatorResponse.observe(this, Observer {
//            Log.i("TESTE", it.toString())
        })
        viewModel.showLoading.observe(this, Observer {

        })
        viewModel.error.observe(this, Observer {

        })
    }


}
