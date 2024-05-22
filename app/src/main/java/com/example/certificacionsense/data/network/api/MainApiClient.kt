package com.example.certificacionsense.data.network.api

import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import com.example.certificacionsense.data.network.retrofit.RetrofitHelper

class MainApiClient {

    private var retrofit = RetrofitHelper.getRetrofit()

    suspend fun getVideoGames():MutableList<VideoGameResponseItem>{

        val response = retrofit.create(MainApiService::class.java).getAllVideoGames()

        return response
    }

}