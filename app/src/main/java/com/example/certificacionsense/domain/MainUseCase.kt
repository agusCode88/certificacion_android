package com.example.certificacionsense.domain

import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import com.example.certificacionsense.data.repository.MainRepositoryImpl

class MainUseCase(private val repositoryImpl: MainRepositoryImpl) {
    suspend fun getAllVideoGames(): MutableList<VideoGameResponseItem>{
        return repositoryImpl.fetchVideoGames()
    }

    suspend fun getVideoGameDetail(idVideoGame: Int): VideoGameResponseItem{
        return repositoryImpl.fetchVideoGameById(idVideoGame)
    }

    suspend fun saveAllVideoGamesDB(videoGames: MutableList<VideoGameResponseItem>){
        return repositoryImpl.saveAllVideoGamesDB(videoGames)
    }
}