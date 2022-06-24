package com.example.chalengejobreadiness.responses.ItemResponse

import com.google.gson.annotations.SerializedName

data class ResponseItem(
    @SerializedName("body") val body : Body
)