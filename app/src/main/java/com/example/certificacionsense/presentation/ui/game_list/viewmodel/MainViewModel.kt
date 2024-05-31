package com.example.certificacionsense.presentation.ui.game_list.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import com.example.certificacionsense.domain.MainUseCase
import com.example.certificacionsense.utils.NetWorkUtils
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: MainUseCase): ViewModel() {

    private var videoGamesList = MutableLiveData<MutableList<VideoGameResponseItem>>()
            val videoGamesLV
                get() = videoGamesList

    private var _isData = MutableLiveData<Boolean>()
            val idDataLV
                get() = _isData


    fun getAllVideoGames(context: Context){
        viewModelScope.launch {
            if(NetWorkUtils.isOnline(context)){
                val videoGames = useCase.getAllVideoGames()
                if(videoGames.isNotEmpty()){
                    useCase.saveAllVideoGamesDB(videoGames)
                    videoGamesList.value = videoGames
                }
            } else{
                val videoGamesDB = useCase.getAllVideoGamesFromDB()
                if(videoGamesDB.isEmpty()){
                    _isData.value = false
                }else{
                    _isData.value = true
                    videoGamesList.value = videoGamesDB
                }

            }
        }

    }

}