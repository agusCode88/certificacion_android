package com.example.certificacionsense.presentation.ui.detail.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.certificacionsense.R
import com.example.certificacionsense.data.local.VideoGameDataBase
import com.example.certificacionsense.data.network.api.MainApiService
import com.example.certificacionsense.data.network.response.VideoGameResponseItem
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
        val repository = MainRepositoryImpl(apiService, database.videoGameDAO())
        val videoGamesUseCase = MainUseCase(repository)

        val viewModelFactory = DetailViewModelFactory(videoGamesUseCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        val idVideoGame = intent.getIntExtra("ID_VIDEO_GAME", -1)
        Log.i("DetailActivity", idVideoGame.toString())

        viewModel.getVideoGameById(idVideoGame, this)

        viewModel.videoGameLV.observe(this) {
            with(it) {
                detailBinding.txtVDName.text = name
                detailBinding.ratingBar.rating = rating.toFloat()
                detailBinding.txtRealeased.text = released
                detailBinding.txtTop.text = metacritic.toString()
                Picasso
                    .get()
                    .load(backgroundImage)
                    .into(detailBinding.imgGame)

                detailBinding.btnSendMail.setOnClickListener {
                    enviarCorreoElectronicoSismo(name)
                }
            }
        }
    }
    private fun enviarCorreoElectronicoSismo(videoGame: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("agus.romero.salazar@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT,"Me interesa un videoJuego")
        intent.putExtra(
            Intent.EXTRA_TEXT,"Hola Agustín, quiero comprar este juego ${ videoGame } " )
        if(intent.resolveActivity(packageManager) != null ){
            startActivity(Intent.createChooser(intent, "Enviar por correo"))
        } else
            Toast.makeText(
                this,
                "Tienes que tener instalada una aplicación de correo",
                Toast.LENGTH_LONG
            ).show()
    }
}