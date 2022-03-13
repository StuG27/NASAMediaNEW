package com.volynkin.nasamedia.data.repositories

import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import com.volynkin.nasamedia.domain.repositories.NASAMediaItemsRepository
import kotlinx.coroutines.flow.Flow

class NASAMediaItemsRepositoryImpl(
    private val localDataSource: NASAMediaItemsLocalDataSource,
    private val remoteDataSource: NASAMediaItemsRemoteDataSource
) : NASAMediaItemsRepository {

    override suspend fun getRemoteNASAMediaItem(keyWords: String): Result<List<NASAMediaItem>> {
        return remoteDataSource.getNASAMediaItems(keyWords)
    }

    override suspend fun getFavoritesNASAMediaItem(): Flow<List<NASAMediaItem>> {
        return localDataSource.getFavoritesNASAMediaItem()
    }

    override suspend fun addToFavorites(NASAMediaItem: NASAMediaItem) {
        localDataSource.addToFavorites(NASAMediaItem)
    }

    override suspend fun removeFromFavorites(NASAMediaItem: NASAMediaItem) {
        localDataSource.removeFromFavorites(NASAMediaItem)
    }
}