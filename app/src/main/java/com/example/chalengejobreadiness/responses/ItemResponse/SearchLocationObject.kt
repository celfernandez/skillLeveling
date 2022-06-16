package com.example.chalengejobreadiness.responses.ResponseItem

import com.google.gson.annotations.SerializedName

data class SearchLocationObject(
    @SerializedName("neighborhood") val neighborhoodObject : NeighborhoodObject
)
