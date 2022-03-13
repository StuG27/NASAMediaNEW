package com.volynkin.nasamedia.data.repositories

import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import kotlinx.coroutines.flow.Flow


interface NASAMediaItemsLocalDataSource {
    suspend fun getFavoritesNASAMediaItem(): Flow<List<NASAMediaItem>>
    suspend fun addToFavorites(NASAMediaItem: NASAMediaItem)
    suspend fun removeFromFavorites(NASAMediaItem: NASAMediaItem)
}