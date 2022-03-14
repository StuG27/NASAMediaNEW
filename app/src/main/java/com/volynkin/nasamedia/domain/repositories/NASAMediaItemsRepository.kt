package com.volynkin.nasamedia.domain.repositories

import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import kotlinx.coroutines.flow.Flow

interface NASAMediaItemsRepository {
    suspend fun getRemoteNASAMediaItem(keyWord: String): Result<List<NASAMediaItem>>
    suspend fun getFavoritesNASAMediaItem(): Flow<List<NASAMediaItem>>
    suspend fun addToFavorites(NASAMediaItem: NASAMediaItem)
    suspend fun removeFromFavorites(NASAMediaItem: NASAMediaItem)
}