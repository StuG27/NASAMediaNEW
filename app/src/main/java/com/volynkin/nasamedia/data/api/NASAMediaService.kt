package com.volynkin.nasamedia.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NASAMediaService {
    @GET("/search")
    fun downloadNASAMediaItems(
        @Query("q") request: String = "",
        @Query("media_type") type: String = "image"
    ): Call<String>
}