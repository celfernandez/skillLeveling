package com.example.chalengejobreadiness.responses.TopTwentyResponse

import com.google.gson.annotations.SerializedName

data class Item (
    @SerializedName("id") val id : String,
    @SerializedName("position") val position : Int,
    @SerializedName("type") val type : String
)