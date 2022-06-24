package com.example.chalengejobreadiness.API

import com.example.chalengejobreadiness.responses.ItemResponse.ItemDescription
import com.example.chalengejobreadiness.responses.ItemResponse.ResponseItem
import com.example.chalengejobreadiness.responses.ResponseTopTwenty
import com.example.chalengejobreadiness.responses.ValidCategoryResponse.ValidCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


const val token = "APP_USR-2129611083943439-062319-90b34627845f2599bd7b4c9f693ad13b-1279976"
interface APIServiceItem {
    /*Returns a ResponseItem for an specific idItem*/
    @Headers("Authorization: Bearer $token")
    @GET("items?")
    suspend fun getItemsFromAPI(@Query(value = "ids", encoded = true) ids : String) : Response<List<ResponseItem>>


    /*Returns a ResponseTopTwenty (list of items) for an specific idCategory searched*/
    @Headers("Authorization: Bearer $token")
    @GET("highlights/MLM/category/{id}/")
    suspend fun getTopItems(@Path("id") id: String): Response<ResponseTopTwenty>

    /*Returns a ResponseValidCategory based on what the user searched*/
    @Headers("Authorization: Bearer $token")
    @GET("sites/MLM/domain_discovery/search?")
    suspend fun getValidCategory(@Query ("limit") limit : Int = 1, @Query("q") q : String) : Response<List<ValidCategory>>

    /*Returns an ItemDescription for an specific idItem*/
    @Headers("Authorization: Bearer $token")
    @GET("items/{id}/description")
    suspend fun getItemDescription(@Path ("id") id: String): Response<ItemDescription>

}