package com.example.certificacionsense.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.certificacionsense.data.network.response.VideoGameResponseItem

@Database(entities = [VideoGameResponseItem::class], version = 1, exportSchema = false)
abstract class VideoGameDataBase: RoomDatabase() {

    abstract fun videoGameDAO(): VideoGameDao

    companion object {
        @Volatile
        private var INSTANCE: VideoGameDataBase? = null

        fun getDatabase(context: Context): VideoGameDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideoGameDataBase::class.java,
                    "videoGame_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}