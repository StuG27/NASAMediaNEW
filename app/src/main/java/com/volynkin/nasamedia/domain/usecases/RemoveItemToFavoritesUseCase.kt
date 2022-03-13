package com.volynkin.nasamedia.domain.usecases

import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import com.volynkin.nasamedia.domain.repositories.NASAMediaItemsRepository

class RemoveItemToFavoritesUseCase(private val NASAMediaItemRepository: NASAMediaItemsRepository) {
    suspend operator fun invoke(NASAMediaItem: NASAMediaItem) =
        NASAMediaItemRepository.removeFromFavorites(NASAMediaItem)
}