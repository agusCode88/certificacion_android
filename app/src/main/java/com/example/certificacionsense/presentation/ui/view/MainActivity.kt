package com.example.certificacionsense.presentation.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.certificacionsense.R
import com.example.certificacionsense.data.network.api.MainApiService
import com.example.certificacionsense.data.network.retrofit.RetrofitHelper
import com.example.certificacionsense.data.repository.MainRepositoryImpl
import com.example.certificacionsense.domain.MainUseCase
import com.example.certificacionsense.presentation.ui.viewmodel.MainViewModel
import com.example.certificacionsense.presentation.ui.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Retrofit and ApiService
        val apiService = RetrofitHelper.getRetrofit().create(MainApiService::class.java)

        // Create Repository and UseCase instances
        val repository = MainRepositoryImpl(apiService)
        val videoGamesUseCase = MainUseCase(repository)

        // Pass UseCase to ViewModelFactory
        val viewModelFactory = MainViewModelFactory(videoGamesUseCase)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // Observe the ViewModel data
        viewModel.videoGamesLV.observe(this) {
            Log.i("GAMES", it.toString())
            // Update UI with the video game list
        }

    }
}