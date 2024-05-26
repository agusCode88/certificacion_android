package com.example.certificacionsense.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.certificacionsense.data.network.response.VideoGameResponseItem

@Dao
interface VideoGameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVideoGames( videoGames: MutableList<VideoGameResponseItem>)
}