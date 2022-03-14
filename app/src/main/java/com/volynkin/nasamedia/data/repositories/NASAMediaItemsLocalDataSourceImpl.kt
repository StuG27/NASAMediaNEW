package com.volynkin.nasamedia.data.repositories

import com.volynkin.nasamedia.data.db.NASAMediaDatabase
import com.volynkin.nasamedia.data.entities.NASAMediaItemsEntityMapper
import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NASAMediaItemsLocalDataSourceImpl @Inject constructor(
    private val database: NASAMediaDatabase,
    private val entityMapper: NASAMediaItemsEntityMapper
) : NASAMediaItemsLocalDataSource {

    override suspend fun getFavoritesNASAMediaItem(): Flow<List<NASAMediaItem>> {
        val savedItemsFlow = database.NASAMediaDao().getSavedNASAMediaItems()
        return savedItemsFlow.map { list ->
            list.map { element ->
                entityMapper.toNASAMediaItems(element)
            }
        }
    }

    override suspend fun addToFavorites(NASAMediaItem: NASAMediaItem) {
        database.NASAMediaDao()
            .saveNASAMediaItem(entityMapper.toRemoteNASAMediaItemEntity(NASAMediaItem))
    }

    override suspend fun removeFromFavorites(NASAMediaItem: NASAMediaItem) {
        database.NASAMediaDao()
            .deleteNASAMediaItem(entityMapper.toRemoteNASAMediaItemEntity(NASAMediaItem))
    }
}