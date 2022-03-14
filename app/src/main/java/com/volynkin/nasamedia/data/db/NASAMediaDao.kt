package com.volynkin.nasamedia.data.db

import androidx.room.*
import com.volynkin.nasamedia.data.entities.RemoteNASAMediaItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NASAMediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNASAMediaItem(remoteNASAMediaItem: RemoteNASAMediaItem)

    @Query("SELECT * FROM `remoteNASAMediaItem`")
    fun getSavedNASAMediaItems(): Flow<List<RemoteNASAMediaItem>>

    @Delete
    suspend fun deleteNASAMediaItem(remoteNASAMediaItem: RemoteNASAMediaItem)
}