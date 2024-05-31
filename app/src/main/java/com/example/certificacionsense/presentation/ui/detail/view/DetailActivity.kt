package com.example.certificacionsense.presentation.ui.detail.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.certificacionsense.R
import com.example.certificacionsense.data.local.VideoGameDataBase
import com.example.certificacionsense.data.network.api.MainApiService
import com.example.certificacionsense.data.network.retrofit.RetrofitHelper
import com.example.certificacionsense.data.repository.MainRepositoryImpl
import com.example.certificacionsense.databinding.ActivityDetailBinding
import com.example.certificacionsense.domain.MainUseCase
import com.example.certificacionsense.presentation.ui.detail.viewmodel.DetailViewModel
import com.example.certificacionsense.presentation.ui.detail.viewmodel.DetailViewModelFactory
import com.example.certificacionsense.presentation.ui.game_list.viewmodel.MainViewModel
import com.example.certificacionsense.presentation.ui.game_list.viewmodel.MainViewModelFactory
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)

        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val apiService = RetrofitHelper.getRetrofit().create(MainApiService::class.java)
        val database = VideoGameDataBase.getDatabase(application)

        // Create Repository and UseCase instances
        val repository = MainRepositoryImpl(apiService,database.videoGameDAO())
        val videoGamesUseCase = MainUseCase(repository)

        val viewModelFactory = DetailViewModelFactory(videoGamesUseCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        val idVideoGame = intent.getIntExtra("ID_VIDEO_GAME",-1)
        Log.i("DetailActivity", idVideoGame.toString())

        viewModel.getVideoGameById(idVideoGame,this)

        viewModel.videoGameLV.observe(this){
            with(it){
                detailBinding.txtVDName.text = name
                detailBinding.ratingBar.rating = rating.toFloat()
                detailBinding.txtRealeased.text = released
                detailBinding.txtTop.text = metacritic.toString()
                Picasso
                    .get()
                    .load(backgroundImage)
                    .into(detailBinding.imgGame)
            }
        }
    }
}