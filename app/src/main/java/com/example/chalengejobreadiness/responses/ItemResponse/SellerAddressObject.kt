package com.example.chalengejobreadiness.responses.ResponseItem

import com.google.gson.annotations.SerializedName

data class SellerAddressObject(
    @SerializedName("search_location") val searchLocationObject : SearchLocationObject
)
