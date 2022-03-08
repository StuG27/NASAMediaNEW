package com.volynkin.nasamedia.data.repositories



interface NASAMediaItemsRemoteDataSource {
    suspend fun getNASAMediaItems(keyWords: String?)
}