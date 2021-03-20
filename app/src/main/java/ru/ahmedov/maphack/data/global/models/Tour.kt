package ru.ahmedov.maphack.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Tour (
    @SerializedName("status") val name  : String,
    @SerializedName("time") val period: String,
    @SerializedName("type") val price: String,
    @SerializedName("address") val image: Int
): Parcelable