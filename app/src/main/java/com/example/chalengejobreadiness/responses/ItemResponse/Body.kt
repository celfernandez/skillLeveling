package com.example.chalengejobreadiness.responses.ResponseItem

import com.google.gson.annotations.SerializedName

data class Body (
    @SerializedName("title") val title : String,
    @SerializedName("price") val price : Int,
    @SerializedName("seller_address") val sellerAddressObject : SellerAddressObject
)
