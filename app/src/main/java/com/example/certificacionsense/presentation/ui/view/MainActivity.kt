package com.example.certificacionsense.presentation.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.certificacionsense.R
import com.example.certificacionsense.data.local.VideoGameDao
import com.example.certificacionsense.data.local.VideoGameDataBase
import com.example.certificacionsense.data.network.api.MainApiService
import com.example.certificacionsense.data.network.retrofit.RetrofitHelper
import com.example.certificacionsense.data.repository.MainRepositoryImpl
import com.example.certificacionsense.databinding.ActivityMainBinding
import com.example.certificacionsense.domain.MainUseCase
import com.example.certificacionsense.presentation.ui.viewmodel.MainViewModel
import com.example.certificacionsense.presentation.ui.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Retrofit and ApiService
        val apiService = RetrofitHelper.getRetrofit().create(MainApiService::class.java)
        val database = VideoGameDataBase.getDatabase(application)

        // Create Repository and UseCase instances
        val repository = MainRepositoryImpl(apiService,database.videoGameDAO())
        val videoGamesUseCase = MainUseCase(repository)

        // Pass UseCase to ViewModelFactory
        val viewModelFactory = MainViewModelFactory(videoGamesUseCase)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val adapter = MainAdapter()
        binding.vgRecycler.adapter = adapter
        binding.vgRecycler.layoutManager = LinearLayoutManager(this)
       // binding.vgRecycler.itemAnimator = null

        // Observe the ViewModel data
        viewModel.videoGamesLV.observe(this) {
            Log.i("GAMES", it.toString())
            adapter.videoGames = it
        }

    }
}