package com.example.easyinvestmvvmtest.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BaseSimulation(
    @SerializedName("investedAmount")
    val investedAmount: Double,

    @SerializedName("yearlyInterestRate")
    val yearlyInterestRate: Double,

    @SerializedName("maturityTotalDays")
    val maturityTotalDays: Int,

    @SerializedName("maturityBusinessDays")
    val maturityBusinessDays: Int,

    @SerializedName("maturityDate")
    val maturityDate: String,

    @SerializedName("rate")
    val rate: Double,

    @SerializedName("isTaxFree")
    val isTaxFree: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(investedAmount)
        parcel.writeDouble(yearlyInterestRate)
        parcel.writeInt(maturityTotalDays)
        parcel.writeInt(maturityBusinessDays)
        parcel.writeString(maturityDate)
        parcel.writeDouble(rate)
        parcel.writeByte(if (isTaxFree) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseSimulation> {
        override fun createFromParcel(parcel: Parcel): BaseSimulation {
            return BaseSimulation(parcel)
        }

        override fun newArray(size: Int): Array<BaseSimulation?> {
            return arrayOfNulls(size)
        }
    }
}