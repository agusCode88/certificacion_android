package com.example.certificacionsense.data.repository

import com.example.certificacionsense.data.model.VideoGame
import com.example.certificacionsense.data.network.response.VideoGameResponseItem

interface MainRepository {
    suspend fun fetchVideoGames(): MutableList<VideoGameResponseItem>
}