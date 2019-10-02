package com.example.easyinvestmvvmtest.ui.Simulate


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.easyinvestmvvmtest.R
import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.data.model.SimulationResult
import com.example.easyinvestmvvmtest.helper.extension.*
import com.example.easyinvestmvvmtest.helper.util.Masks
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
        })

        viewModel.loading().observe(this, Observer {
            button_start_simulation.isEnabled = false
            loading.setVisibility(it)
            overlay.setVisibility(it)
        })

        viewModel.success().observe(this, Observer {
            if (it){
                val bundle = bundleOf(ResultSimulationFragment.ARGUMENT_RESULT to resultSimulation)
                findNavController().navigate(
                    R.id.action_SimulateFragment_to_ResultSimulateFragment,
                    bundle
                )
            }else{
                Toast.makeText(context, getString(R.string.text_message_error), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.error().observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            label_value_amount_application.text?.clear()
            label_value_percent_cdi_investment.text?.clear()
            label_value_due_date_investment.text?.clear()
        })
    }

    private fun configureViews() {
        button_start_simulation.isEnabled = false
        label_value_amount_application.currencyWatcher(label_value_amount_application)
        label_value_percent_cdi_investment.percentWatcher(label_value_percent_cdi_investment)
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


