package com.example.easyinvestmvvmtest.data.repository.Calculator

import com.example.easyinvestmvvmtest.data.model.CustomResponse
import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.data.model.SimulationResult
import com.example.easyinvestmvvmtest.data.repository.CalculatorSimulateApi

class CalculatorRepository(private val api: CalculatorSimulateApi) :
    ICalculatorRepository {
    override suspend fun getSimulation(investment: Investment): CustomResponse<SimulationResult> {
        return try {
            val result = api.getCalculatorSimulateAsync(
                investedAmount = investment.investedAmount,
                index = investment.index,
                rate = investment.rate,
                isTaxFree = investment.isTaxFree,
                maturityDate = investment.maturityDate
            )
            CustomResponse.Success(result)
        } catch (ex: Exception) {
            CustomResponse.Error(ex)
        }
    }
}