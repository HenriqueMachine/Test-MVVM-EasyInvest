package com.example.easyinvestmvvmtest.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SimulationResult(

    @SerializedName("investmentParameter")
    val investmentParameter: BaseSimulation?,

    @SerializedName("grossAmount")
    val grossAmount: Double,

    @SerializedName("taxesAmount")
    val taxAmount: Double,

    @SerializedName("netAmount")
    val netAmount: Double,

    @SerializedName("grossAmountProfit")
    val grossAmountProfit: Double,

    @SerializedName("netAmountProfit")
    val netAmountProfit: Double,

    @SerializedName("annualGrossRateProfit")
    val annualGrossRateProfit: Double,

    @SerializedName("monthlyGrossRateProfit")
    val MonthlyGrossRateProfit: Double,

    @SerializedName("dailyGrossRateProfit")
    val dailyGrossRateProfit: Double,

    @SerializedName("taxesRate")
    val taxRate: Double,

    @SerializedName("rateProfit")
    val rateProfit: Double,

    @SerializedName("annualNetRateProfit")
    val annualNetRateProfit: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(BaseSimulation::class.java.classLoader),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(investmentParameter, flags)
        parcel.writeDouble(grossAmount)
        parcel.writeDouble(taxAmount)
        parcel.writeDouble(netAmount)
        parcel.writeDouble(grossAmountProfit)
        parcel.writeDouble(netAmountProfit)
        parcel.writeDouble(annualGrossRateProfit)
        parcel.writeDouble(MonthlyGrossRateProfit)
        parcel.writeDouble(dailyGrossRateProfit)
        parcel.writeDouble(taxRate)
        parcel.writeDouble(rateProfit)
        parcel.writeDouble(annualNetRateProfit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SimulationResult> {
        override fun createFromParcel(parcel: Parcel): SimulationResult {
            return SimulationResult(parcel)
        }

        override fun newArray(size: Int): Array<SimulationResult?> {
            return arrayOfNulls(size)
        }
    }
}