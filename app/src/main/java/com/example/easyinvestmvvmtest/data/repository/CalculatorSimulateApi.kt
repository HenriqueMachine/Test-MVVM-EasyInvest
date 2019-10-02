package com.example.easyinvestmvvmtest.data.repository

import com.example.easyinvestmvvmtest.data.model.SimulationResult
import retrofit2.http.GET
import retrofit2.http.Query

interface CalculatorSimulateApi {

    @GET("calculator/simulate")
    suspend fun getCalculatorSimulateAsync(
        @Query("investedAmount") investedAmount: Double,
        @Query("index") index: String,
        @Query("maturityDate") maturityDate: String,
        @Query("rate") rate: Double,
        @Query("isTaxFree") isTaxFree: Boolean
    ): SimulationResult
}