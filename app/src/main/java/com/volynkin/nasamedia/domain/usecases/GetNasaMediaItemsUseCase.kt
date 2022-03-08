package com.volynkin.nasamedia.domain.usecases

import com.volynkin.nasamedia.domain.repositories.NASAMediaItemsRepository

class GetNasaMediaItemsUseCase(private val NASAMediaItemRepository: NASAMediaItemsRepository) {
    suspend operator fun invoke(keyWords: String) =
        NASAMediaItemRepository.getRemoteNASAMediaItem(keyWords)
}