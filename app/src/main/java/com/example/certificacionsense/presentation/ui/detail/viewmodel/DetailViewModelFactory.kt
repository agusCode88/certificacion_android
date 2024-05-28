package com.example.certificacionsense.presentation.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.certificacionsense.domain.MainUseCase

class DetailViewModelFactory(private val videoGamesUseCase: MainUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(videoGamesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}