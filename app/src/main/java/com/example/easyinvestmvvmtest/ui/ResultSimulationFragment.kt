package com.example.easyinvestmvvmtest.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.easyinvestmvvmtest.R
import com.example.easyinvestmvvmtest.data.model.SimulationResult

/**
 * A simple [Fragment] subclass.
 */
class ResultSimulationFragment : Fragment() {

    companion object {
        const val ARGUMENT_RESULT = "ARGUMENT"
    }

    private var resultSimulation: SimulationResult? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_simulation, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultSimulation = arguments?.getParcelable(ARGUMENT_RESULT)

    }


}
