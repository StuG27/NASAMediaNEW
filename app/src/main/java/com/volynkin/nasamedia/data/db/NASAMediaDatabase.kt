package com.volynkin.nasamedia.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.volynkin.nasamedia.data.entities.RemoteNASAMediaItem

@Database(entities = [RemoteNASAMediaItem::class], version = 0)
abstract class NASAMediaDatabase : RoomDatabase() {
    abstract fun NASAMediaDao(): NASAMediaDao

    companion object {
        @Volatile
        private var INSTANCE: NASAMediaDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): NASAMediaDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NASAMediaDatabase::class.java, "nasa_media_items.db"
            ).build()
    }
}