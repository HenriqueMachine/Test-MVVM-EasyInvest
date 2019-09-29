package com.example.easyinvestmvvmtest.data.repository.Calculator

import com.example.easyinvestmvvmtest.data.model.CustomResponse
import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.data.model.SimulationResult

interface ICalculatorRepository {
    suspend fun getSimulation(investment: Investment): CustomResponse<SimulationResult>
}