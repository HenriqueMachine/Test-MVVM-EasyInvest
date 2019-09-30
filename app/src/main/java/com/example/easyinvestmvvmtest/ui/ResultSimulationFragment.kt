package com.example.easyinvestmvvmtest.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.easyinvestmvvmtest.R
import com.example.easyinvestmvvmtest.data.model.SimulationResult
import com.example.easyinvestmvvmtest.helper.extension.maskFormatterCurrency
import kotlinx.android.synthetic.main.fragment_result_simulation.*


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
        configureScreen()

    }

    private fun configureScreen() {
        with(resultSimulation){

            label_value_result_simulation.text =  maskFormatterCurrency(this?.grossAmount)
            label_text_total_income.text = context?.getString(R.string.text_total_income_with_value,maskFormatterCurrency(this?.grossAmountProfit))

            //investmentParameter
            label_value_initially_applied.text = maskFormatterCurrency(this?.investmentParameter?.investedAmount)
            label_value_gross_investment.text = maskFormatterCurrency(this?.grossAmount)
            label_value_yield.text = maskFormatterCurrency(this?.grossAmountProfit)
            label_value_ir_investment.text = "${maskFormatterCurrency(this?.taxAmount)}" + "${this?.taxRate}"
            label_value_net_investment.text = maskFormatterCurrency(this?.netAmount)
            //SimulationResult
            label_value_redemption_date.text = this?.investmentParameter?.maturityDate
            label_value_busy_days.text = this?.investmentParameter?.maturityTotalDays.toString()
            label_value_monthly_income.text = this?.MonthlyGrossRateProfit.toString()
            label_value_cdi_percent_investment.text = this?.investmentParameter?.rate.toString()
            label_value_annual_profitability.text = this?.investmentParameter?.yearlyInterestRate.toString()
            label_value_period_profitability.text = this?.annualNetRateProfit.toString()
        }
        button_simulate_again.setOnClickListener {
            findNavController().navigate(R.id.action_ResultSimulateFragment_to_SimulateFragment)
        }
    }

}
