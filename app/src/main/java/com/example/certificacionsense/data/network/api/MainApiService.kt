package com.example.certificacionsense.data.network.api

import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApiService {
    @GET("games")
    suspend fun getAllVideoGames(): MutableList<VideoGameResponseItem>

    @GET("games/{id}")
    suspend fun getVideoGameById(@Path("id") idVideoGame: Int): VideoGameResponseItem
}