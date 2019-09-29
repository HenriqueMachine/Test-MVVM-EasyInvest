package com.example.easyinvestmvvmtest.data

import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.data.model.SimulationResult

interface ICalculatorRepository {
    suspend fun getSimulation(investment: Investment): Result<SimulationResult>
}