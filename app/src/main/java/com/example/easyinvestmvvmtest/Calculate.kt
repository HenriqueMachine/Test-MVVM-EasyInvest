package com.example.easyinvestmvvmtest

import com.google.gson.annotations.SerializedName

data class Calculate(
    @SerializedName( "investedAmount" )
    val investedAmount: Double,
    @SerializedName( "yearlyInterestRate" )
    val yearlyInterestRate: Double,
    @SerializedName( "maturityTotalDays" )
    val maturityTotalDays: Int,
    @SerializedName( "maturityBusinessDays" )
    val maturityBusinessDays: Int,
    @SerializedName( "maturityDate" )
    val maturityDate: String,
    @SerializedName( "rate" )
    val rate: Double,
    @SerializedName( "isTaxFree" )
    val isTaxFree: Boolean,

    @SerializedName( "grossAmount" )
    val grossAmount: String,
    @SerializedName( "taxAmount" )
    val taxAmount: String,
    @SerializedName( "netAmount" )
    val netAmount: String,
    @SerializedName( "grossAmountProfit" )
    val grossAmountProfit: String,
    @SerializedName( "netAmountProfit" )
    val netAmountProfit: String,
    @SerializedName( "annualGrossRateProfit" )
    val annualGrossRateProfit: String,
    @SerializedName( "MonthlyGrossRateProfit" )
    val MonthlyGrossRateProfit: String,
    @SerializedName( "dailyGrossRateProfit" )
    val dailyGrossRateProfit: String,
    @SerializedName( "taxRate" )
    val taxRate: String,
    @SerializedName( "rateProfit" )
    val rateProfit: String,
    @SerializedName( "annualNetRateProfit" )
    val annualNetRateProfit: String
)