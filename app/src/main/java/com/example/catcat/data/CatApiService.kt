package com.example.catcat.data

import com.example.catcat.model.CatImage
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("v1/images/search")
    suspend fun getCats(@Query("limit") limit: Int = 10): List<CatImage>
}