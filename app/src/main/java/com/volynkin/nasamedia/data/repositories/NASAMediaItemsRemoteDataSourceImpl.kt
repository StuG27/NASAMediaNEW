package com.volynkin.nasamedia.data.repositories

import com.volynkin.nasamedia.data.api.NASAMediaItemsResponseMapper
import com.volynkin.nasamedia.data.api.NASAMediaService
import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class NASAMediaItemsRemoteDataSourceImpl @Inject constructor(
    private val service: NASAMediaService,
    private val responseMapper: NASAMediaItemsResponseMapper
) : NASAMediaItemsRemoteDataSource {

    override suspend fun getNASAMediaItems(keyWord: String): Result<List<NASAMediaItem>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.downloadNASAMediaItems(keyWord)
                if (response.isSuccessful) {
                    return@withContext Result.success(responseMapper.toNASAMediaItemList(response.body()!!))
                } else {
                    return@withContext Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }
}