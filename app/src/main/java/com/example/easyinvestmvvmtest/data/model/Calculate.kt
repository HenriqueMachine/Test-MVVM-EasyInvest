package com.example.easyinvestmvvmtest.data.model

import com.google.gson.annotations.SerializedName

data class Calculate(

    @SerializedName( "investmentParameter" )
    val investmentParameter: InvestmentBase,

    @SerializedName( "grossAmount" )
    val grossAmount: Double,

    @SerializedName( "taxesAmount" )
    val taxAmount: Double,

    @SerializedName( "netAmount" )
    val netAmount: Double,

    @SerializedName( "grossAmountProfit" )
    val grossAmountProfit: Double,

    @SerializedName( "netAmountProfit" )
    val netAmountProfit: Double,

    @SerializedName( "annualGrossRateProfit" )
    val annualGrossRateProfit: Double,

    @SerializedName( "monthlyGrossRateProfit" )
    val MonthlyGrossRateProfit: Double,

    @SerializedName( "dailyGrossRateProfit" )
    val dailyGrossRateProfit: Double,

    @SerializedName( "taxesRate" )
    val taxRate: Double,

    @SerializedName( "rateProfit" )
    val rateProfit: Double,

    @SerializedName( "annualNetRateProfit" )
    val annualNetRateProfit: Double
)