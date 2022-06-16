package com.example.chalengejobreadiness.API

import com.example.chalengejobreadiness.responses.ItemResponse.ResponseItem
import com.example.chalengejobreadiness.responses.ResponseValidCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
        suspend fun getItemsFromAPI(@Url query : String) : Response<ResponseItem>

}