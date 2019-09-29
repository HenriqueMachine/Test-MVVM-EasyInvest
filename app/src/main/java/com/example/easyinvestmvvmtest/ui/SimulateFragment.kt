package com.example.easyinvestmvvmtest.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.easyinvestmvvmtest.R
import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.data.model.SimulationResult
import com.example.easyinvestmvvmtest.helper.Masks
import kotlinx.android.synthetic.main.fragment_simulate.*
import org.koin.android.viewmodel.ext.android.viewModel

class SimulateFragment : Fragment() {

    private val viewModel: SimulateViewModel by viewModel()
    private var resultSimulation: SimulationResult? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simulate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViews()
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.calculatorResponse.observe(this, Observer {
            resultSimulation = it
            val bundle = bundleOf(ResultSimulationFragment.ARGUMENT_RESULT to resultSimulation)
            findNavController().navigate(R.id.action_SimulateFragment_to_ResultSimulateFragment, bundle)
        })
    }

    private fun configureViews() {
        button_start_simulation.isEnabled = true

        button_start_simulation.setOnClickListener {
//            viewModel.simulation(
//                Investment(
//                    32323.0,
//                    "CDI",
//                    123.0,
//                    false,
//                    "2023-03-03"
//                )
//            )
        }

        label_value_due_date_investment.addTextChangedListener(
            Masks.mask(
                label_value_due_date_investment,
                Masks.DATE_MASK
            )
        )
    }


}
