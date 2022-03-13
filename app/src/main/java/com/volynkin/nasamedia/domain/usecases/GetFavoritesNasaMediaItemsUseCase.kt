package com.volynkin.nasamedia.domain.usecases

import com.volynkin.nasamedia.domain.repositories.NASAMediaItemsRepository

class GetFavoritesNasaMediaItemsUseCase(private val NASAMediaItemRepository: NASAMediaItemsRepository) {
    suspend operator fun invoke() =
        NASAMediaItemRepository.getFavoritesNASAMediaItem()
}