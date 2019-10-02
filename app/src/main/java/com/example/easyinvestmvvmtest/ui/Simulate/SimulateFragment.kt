package com.example.easyinvestmvvmtest.ui.Simulate


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
import com.example.easyinvestmvvmtest.helper.util.CurrencyTextWatcher
import com.example.easyinvestmvvmtest.helper.util.Masks
import com.example.easyinvestmvvmtest.helper.util.PercentTextWatcher
import com.example.easyinvestmvvmtest.helper.extension.currencyToDouble
import com.example.easyinvestmvvmtest.helper.extension.getDate
import com.example.easyinvestmvvmtest.helper.extension.listenerChange
import com.example.easyinvestmvvmtest.helper.extension.onlyNumbers
import com.example.easyinvestmvvmtest.ui.ResultSimulate.ResultSimulationFragment
import kotlinx.android.synthetic.main.fragment_simulate.*
import org.koin.android.viewmodel.ext.android.viewModel

class SimulateFragment : Fragment() {

    private val viewModel: SimulateViewModel by viewModel()
    private var resultSimulation: SimulationResult? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simulate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViews()
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.calculator().observe(this, Observer {
            resultSimulation = it
            val bundle = bundleOf(ResultSimulationFragment.ARGUMENT_RESULT to resultSimulation)
            findNavController().navigate(
                R.id.action_SimulateFragment_to_ResultSimulateFragment,
                bundle
            )
        })

    }

    private fun configureViews() {
        button_start_simulation.isEnabled = false
        label_value_amount_application.addTextChangedListener(context?.let {
            CurrencyTextWatcher(
                label_value_amount_application,
                it
            )
        })
        label_value_percent_cdi_investment.addTextChangedListener(context?.let {
            PercentTextWatcher(
                label_value_percent_cdi_investment,
                it
            )
        })
        label_value_due_date_investment.addTextChangedListener(
            Masks.mask(
                label_value_due_date_investment,
                Masks.DATE_MASK
            )
        )
        listerChangesEditText()
        button_start_simulation.setOnClickListener {
            val maturityDate: String = getDate(label_value_due_date_investment.text.toString())
            val amountInvestment: Double =
                label_value_amount_application.text.toString().currencyToDouble()
            val cdiInvestment: Double =
                label_value_percent_cdi_investment.text.toString().onlyNumbers().toDouble()

            viewModel.simulation(
                Investment(
                    amountInvestment,
                    "CDI",
                    cdiInvestment,
                    false,
                    maturityDate
                )
            )

        }

    }

    private fun listerChangesEditText() {
        label_value_amount_application.listenerChange {
            validateFields()
        }
        label_value_percent_cdi_investment.listenerChange {
            validateFields()
        }
        label_value_due_date_investment.listenerChange {
            validateFields()
        }
    }

    private fun validateFields() {
        button_start_simulation.isEnabled =
            label_value_due_date_investment.text.toString().isNotEmpty() &&
                    label_value_amount_application.text.toString().isNotEmpty() &&
                    label_value_percent_cdi_investment.text.toString().isNotEmpty()
    }

}


