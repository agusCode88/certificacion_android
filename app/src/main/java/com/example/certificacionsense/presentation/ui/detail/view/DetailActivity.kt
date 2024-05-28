package com.example.certificacionsense.presentation.ui.detail.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.certificacionsense.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val idVideoGame = intent.getIntExtra("ID_VIDEO_GAME",-1)

        Log.i("DetailActivity", idVideoGame.toString())

    }
}