package com.example.easyinvestmvvmtest

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CalculatorSimulateApi {

    @GET("calculator/simulate")
    suspend fun getCalculatorSimulateAsync(
        @Query("investedAmount") investedAmount: Double,
        @Query("index") index: String,
        @Query("maturityDate") maturityDate: String,
        @Query("rate") rate: Int,
        @Query("isTaxFree") isTaxFree: Boolean
    ): Deferred<Calculate>
}