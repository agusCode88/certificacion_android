package com.example.certificacionsense.data.repository

import com.example.certificacionsense.data.model.VideoGame
import com.example.certificacionsense.data.network.api.MainApiService
import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl(private var apiService: MainApiService )  : MainRepository{

    override suspend fun fetchVideoGames(): MutableList<VideoGameResponseItem> {
        return withContext(Dispatchers.IO){
            val videoGames = apiService.getAllVideoGames()
            videoGames
        }
    }

}