package com.example.chalengejobreadiness.responses

import com.example.chalengejobreadiness.responses.TopTwentyResponse.Item
import com.google.gson.annotations.SerializedName

data class ResponseTopTwenty (
    @SerializedName("content") val content : List <Item>,
        )