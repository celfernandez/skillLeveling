package com.example.chalengejobreadiness.responses

import com.example.chalengejobreadiness.responses.ResponseTopTwenty.Content
import com.google.gson.annotations.SerializedName

data class ResponseTopTwenty (
    @SerializedName("content") val top : List <Content>
        )

