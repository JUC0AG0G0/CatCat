package com.example.catcat.data

import com.example.catcat.model.CatImage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatRepository {
    private val api: CatApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApiService::class.java)
    }

    suspend fun fetchCats(): List<CatImage> = api.getCats()
}