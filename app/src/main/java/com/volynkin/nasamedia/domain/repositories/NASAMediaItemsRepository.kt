package com.volynkin.nasamedia.domain.repositories

import com.volynkin.nasamedia.domain.entities.NASAMediaItem

interface NASAMediaItemsRepository {
    suspend fun getRemoteNASAMediaItem(keyWords: String): Result<List<NASAMediaItem>>
}