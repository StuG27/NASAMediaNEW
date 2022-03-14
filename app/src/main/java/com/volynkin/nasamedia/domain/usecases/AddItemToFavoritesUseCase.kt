package com.volynkin.nasamedia.domain.usecases

import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import com.volynkin.nasamedia.domain.repositories.NASAMediaItemsRepository
import javax.inject.Inject

class AddItemToFavoritesUseCase @Inject constructor(private val NASAMediaItemRepository: NASAMediaItemsRepository) {
    suspend operator fun invoke(NASAMediaItem: NASAMediaItem) =
        NASAMediaItemRepository.addToFavorites(NASAMediaItem)
}