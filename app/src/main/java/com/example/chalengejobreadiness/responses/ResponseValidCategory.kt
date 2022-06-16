package com.example.chalengejobreadiness.responses

import com.google.gson.annotations.SerializedName

data class validCategoryResponse (
    @SerializedName("category_id") var categoryId : String
    )