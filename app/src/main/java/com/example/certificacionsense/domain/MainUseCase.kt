package com.example.certificacionsense.domain

import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import com.example.certificacionsense.data.repository.MainRepositoryImpl

class MainUseCase(private val repositoryImpl: MainRepositoryImpl) {

    suspend fun getAllVideoGames(): MutableList<VideoGameResponseItem>{
        return repositoryImpl.fetchVideoGames()
    }
}