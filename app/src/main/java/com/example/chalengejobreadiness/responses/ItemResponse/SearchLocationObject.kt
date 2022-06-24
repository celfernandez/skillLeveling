package com.example.chalengejobreadiness.responses.ItemResponse

import com.google.gson.annotations.SerializedName

data class SearchLocationObject(
    @SerializedName("neighborhood") val neighborhoodObject : NeighborhoodObject
)
