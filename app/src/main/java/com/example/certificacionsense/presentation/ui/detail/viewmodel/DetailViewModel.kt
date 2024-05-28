package com.example.certificacionsense.presentation.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import com.example.certificacionsense.domain.MainUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val videoGamesUseCase: MainUseCase): ViewModel() {

    private val _videoGameById = MutableLiveData<VideoGameResponseItem>()
    val videoGameLV: LiveData<VideoGameResponseItem>
        get() = _videoGameById


    fun getVideoGameByID(videoVideoGame: Int){
        viewModelScope.launch {
            videoGamesUseCase.getVideoGameDetail(videoVideoGame)
        }
    }

}