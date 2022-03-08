package com.volynkin.nasamedia.data.repositories

import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import com.volynkin.nasamedia.domain.repositories.NASAMediaItemsRepository

class NASAMediaItemsRepositoryImpl(
    private val localDataSource: NASAMediaItemsLocalDataSource,
    private val remoteDataSource: NASAMediaItemsRemoteDataSource
) : NASAMediaItemsRepository {

    override suspend fun getRemoteNASAMediaItem(keyWords: String): Result<List<NASAMediaItem>> {
        return remoteDataSource.getNASAMediaItems(keyWords)
    }
}