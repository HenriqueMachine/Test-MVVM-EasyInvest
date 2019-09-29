package com.example.easyinvestmvvmtest.data

import com.example.easyinvestmvvmtest.data.model.Calculate
import com.example.easyinvestmvvmtest.data.model.Investment

interface ICalculatorRepository {
        suspend fun getSimulation(investment: Investment): Result<Calculate>
}