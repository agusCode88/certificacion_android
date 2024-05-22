package com.example.certificacionsense.data.network.api

import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import retrofit2.http.GET

interface MainApiService {
    @GET("games")
    suspend fun getAllVideoGames(): MutableList<VideoGameResponseItem>
}