package com.example.chalengejobreadiness.responses.ItemResponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemDescription (
    @SerializedName("plain_text") val plainText : String?
        ):Parcelable