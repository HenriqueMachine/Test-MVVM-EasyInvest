package com.example.easyinvestmvvmtest.data

import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.data.model.SimulationResult

class CalculatorRepository(private val api: CalculatorSimulateApi) :
    ICalculatorRepository {
    override suspend fun getSimulation(investment: Investment): Result<SimulationResult> {
        return try {
            val result = api.getCalculatorSimulateAsync(
                investedAmount = investment.investedAmount,
                index = investment.index,
                rate = investment.rate,
                isTaxFree = investment.isTaxFree,
                maturityDate = investment.maturityDate
            )
            Result.Success(result)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}