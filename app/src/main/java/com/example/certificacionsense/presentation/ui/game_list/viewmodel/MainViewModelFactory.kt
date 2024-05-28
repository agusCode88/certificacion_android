package com.example.certificacionsense.presentation.ui.game_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.certificacionsense.domain.MainUseCase

class MainViewModelFactory(private val videoGamesUseCase: MainUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(videoGamesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}