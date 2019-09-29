package com.example.easyinvestmvvmtest.data.model

import com.google.gson.annotations.SerializedName

data class InvestmentBase(
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
    val isTaxFree: Boolean
)