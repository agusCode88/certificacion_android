package com.example.certificacionsense.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.certificacionsense.data.network.response.VideoGameResponseItem

@Dao
interface VideoGameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVideoGames( videoGames: MutableList<VideoGameResponseItem>)

    @Query("SELECT * FROM VIDEOGAMES")
    suspend fun getAllVideoGames(): MutableList<VideoGameResponseItem>

    @Query("SELECT * FROM videogames WHERE id = :videoGameId")
    suspend fun getVideoGameById(videoGameId: Int): VideoGameResponseItem
}