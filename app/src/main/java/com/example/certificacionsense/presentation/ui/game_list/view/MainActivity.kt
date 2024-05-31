package com.example.certificacionsense.presentation.ui.game_list.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.certificacionsense.data.local.VideoGameDataBase
import com.example.certificacionsense.data.network.api.MainApiService
import com.example.certificacionsense.data.network.retrofit.RetrofitHelper
import com.example.certificacionsense.data.repository.MainRepositoryImpl
import com.example.certificacionsense.databinding.ActivityMainBinding
import com.example.certificacionsense.domain.MainUseCase
import com.example.certificacionsense.presentation.ui.detail.view.DetailActivity
import com.example.certificacionsense.presentation.ui.game_list.viewmodel.MainViewModel
import com.example.certificacionsense.presentation.ui.game_list.viewmodel.MainViewModelFactory

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
        viewModel.videoGamesLV.observe(this) { it ->
            Log.i("GAMES", it.toString())
            adapter.videoGames = it

            adapter.onItemClickListener = { videoGame ->
                val idVideoGame = videoGame.id
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("ID_VIDEO_GAME", idVideoGame)
                }
                startActivity(intent)

            }
        }

    }
}