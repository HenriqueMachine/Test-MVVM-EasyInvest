package com.example.easyinvestmvvmtest.ui.ResultSimulate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.easyinvestmvvmtest.R
import com.example.easyinvestmvvmtest.data.model.SimulationResult
import com.example.easyinvestmvvmtest.helper.extension.formatToDate
import com.example.easyinvestmvvmtest.helper.extension.maskFormatterCurrency
import com.example.easyinvestmvvmtest.helper.extension.toPercent
import kotlinx.android.synthetic.main.fragment_result_simulation.*
import java.util.*

class ResultSimulationFragment : Fragment() {

    companion object {
        const val ARGUMENT_RESULT = "ARGUMENT"
    }

    private var resultSimulation: SimulationResult? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result_simulation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultSimulation = arguments?.getParcelable(ARGUMENT_RESULT)
        configureScreen()

    }

    private fun configureScreen() {
        with(resultSimulation) {

            label_value_result_simulation.text = maskFormatterCurrency(this?.grossAmount)
            label_text_total_income.text = context?.getString(
                R.string.text_total_income_with_value,
                maskFormatterCurrency(this?.grossAmountProfit)
            )

            //investmentParameter
            label_value_initially_applied.text =
                maskFormatterCurrency(this?.investmentParameter?.investedAmount)
            label_value_gross_investment.text = maskFormatterCurrency(this?.grossAmount)
            label_value_yield.text = maskFormatterCurrency(this?.grossAmountProfit)
            label_value_ir_investment.text = getString(
                R.string.text_value_ir,
                maskFormatterCurrency(this?.taxAmount),
                this?.taxRate?.toPercent()
            )
            label_value_net_investment.text = maskFormatterCurrency(this?.netAmount)
            //SimulationResult
            label_value_redemption_date.text =
                this?.investmentParameter?.maturityDate?.formatToDate(
                    Locale.getDefault()
                )
            label_value_busy_days.text = this?.investmentParameter?.maturityTotalDays.toString()
            label_value_monthly_income.text = this?.MonthlyGrossRateProfit?.toPercent()
            label_value_cdi_percent_investment.text = this?.investmentParameter?.rate?.toPercent()
            label_value_annual_profitability.text = this?.annualNetRateProfit?.toPercent()
            label_value_period_profitability.text =
                this?.investmentParameter?.yearlyInterestRate?.toPercent()
        }
        button_simulate_again.setOnClickListener {
            findNavController().navigate(R.id.action_ResultSimulateFragment_to_SimulateFragment)
        }
    }

}
