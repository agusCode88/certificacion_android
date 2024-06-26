package com.example.certificacionsense.data.repository

import com.example.certificacionsense.data.model.VideoGame
import com.example.certificacionsense.data.network.response.VideoGameResponseItem

interface MainRepository {
    suspend fun fetchVideoGames(): MutableList<VideoGameResponseItem>
    suspend fun fetchVideoGameById(idVideoGame: Int): VideoGameResponseItem
    suspend fun getAllVideoGamesDB(): MutableList<VideoGameResponseItem>
    suspend fun saveAllVideoGamesDB(videoGameResponseItem: MutableList<VideoGameResponseItem>)
    suspend fun videoGameByIdFromDB(videoGameId: Int): VideoGameResponseItem?

}