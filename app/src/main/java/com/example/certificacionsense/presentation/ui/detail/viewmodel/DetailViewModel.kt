package com.example.certificacionsense.presentation.ui.detail.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import com.example.certificacionsense.domain.MainUseCase
import com.example.certificacionsense.utils.NetWorkUtils
import kotlinx.coroutines.launch

class DetailViewModel(private val videoGamesUseCase: MainUseCase): ViewModel() {

    private val _videoGameById = MutableLiveData<VideoGameResponseItem>()
    val videoGameLV: MutableLiveData<VideoGameResponseItem>
        get() = _videoGameById


    fun getVideoGameByID(idVideoGame: Int) {
         viewModelScope.launch {
            val videoGame = videoGamesUseCase.getVideoGameDetail(idVideoGame)
            _videoGameById.value = videoGame
        }
    }

    fun getVideoGameById(idVideoGame: Int,context: Context){
        viewModelScope.launch {
            if(NetWorkUtils.isOnline(context)){
                val videoGame = videoGamesUseCase.getVideoGameDetail(idVideoGame)
                _videoGameById.value = videoGame
            }else{
                val videoGame = videoGamesUseCase.getDetailVideoGameFromDB(idVideoGame)
                _videoGameById.value = videoGame
            }
        }
    }

}