package com.geeks.hmgeeks_52

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/getPercentage")
    fun getLovePercentage(
        @Query("fname") firstName: String,
        @Query("sname") secondName: String
    ): Call<LoveResult>
}