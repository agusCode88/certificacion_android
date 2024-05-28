package com.example.certificacionsense.presentation.ui.game_list.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.certificacionsense.data.network.response.VideoGameResponseItem
import com.example.certificacionsense.databinding.GameItemBinding
import com.squareup.picasso.Picasso

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

   lateinit var onItemClickListener: (VideoGameResponseItem) -> Unit

    var videoGames = mutableListOf<VideoGameResponseItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value){
            field = value
            this.notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoGameResponseItem = videoGames[position]
        holder.bindVideoGame(videoGameResponseItem)

    }

    override fun getItemCount(): Int {
        return videoGames.size
    }

    inner class ViewHolder(private var binding: GameItemBinding): RecyclerView.ViewHolder(binding.root){
         fun bindVideoGame(videoGame : VideoGameResponseItem){

             binding.imgVideoGame.setImageResource(0) // O algún placeholder
             Picasso.get()
                 .load(videoGame.backgroundImage)
                 .fit()
                 .centerCrop()
                 .into(binding.imgVideoGame)
             binding.txtNameVideoGame.text = videoGame.name
             binding.txtReleasedVideoGame.text = videoGame.released
             binding.txtRatingVideoGame.text = videoGame.rating.toString()

             // Asegúrate de que los parámetros de layout no cambien inesperadamente
             val layoutParams = binding.root.layoutParams as RecyclerView.LayoutParams
             layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT
             binding.root.layoutParams = layoutParams

             clickVideoGameListener(videoGame)

         }

        private fun clickVideoGameListener(videoGame: VideoGameResponseItem){
            binding.root.setOnClickListener {
                if(::onItemClickListener.isInitialized)
                    onItemClickListener(videoGame)
                else
                    Log.e("ListVG","Listener not initialized")
            }
        }
    }
}