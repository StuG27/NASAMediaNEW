package com.volynkin.nasamedia.data.repositories

import com.volynkin.nasamedia.domain.entities.NASAMediaItem


interface NASAMediaItemsRemoteDataSource {
    suspend fun getNASAMediaItems(keyWord: String): Result<List<NASAMediaItem>>
}