package ru.ahmedov.maphack.data.global.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Task (
    @SerializedName("status") val status  : String,
    @SerializedName("time") val time: String,
    @SerializedName("type") val type: String,
    @SerializedName("address") val address: String
): Parcelable