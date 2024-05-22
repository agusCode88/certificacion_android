package com.example.certificacionsense.presentation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.certificacionsense.data.model.VideoGame
import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import com.example.certificacionsense.domain.MainUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: MainUseCase): ViewModel() {

    private var videoGamesList = MutableLiveData<MutableList<VideoGameResponseItem>>()
    val videoGamesLV
        get() = videoGamesList

    init {
        viewModelScope.launch {
            videoGamesList.value = useCase.getAllVideoGames()
        }
    }

}