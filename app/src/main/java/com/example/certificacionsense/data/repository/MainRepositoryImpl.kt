package com.example.certificacionsense.data.repository

import com.example.certificacionsense.data.local.VideoGameDao
import com.example.certificacionsense.data.network.api.MainApiService
import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl(
    private var apiService: MainApiService,
    private var videoGameDao: VideoGameDao
)  : MainRepository{

    override suspend fun fetchVideoGames(): MutableList<VideoGameResponseItem> {
        return withContext(Dispatchers.IO){
            val videoGames = apiService.getAllVideoGames()
            if(videoGames.isNotEmpty()){
                saveAllVideoGamesDB(videoGames)
            }
            videoGames
        }
    }

    override suspend fun fetchVideoGameById(idVideoGame: Int): VideoGameResponseItem {
        return withContext(Dispatchers.IO){
            val videoGame = apiService.getVideoGameById(idVideoGame)
            videoGame
        }
    }

    override suspend fun saveAllVideoGamesDB(videoGameResponseItem: MutableList<VideoGameResponseItem>) {
        return withContext(Dispatchers.IO){
            videoGameDao.insertAllVideoGames(videoGameResponseItem)
        }
    }

}