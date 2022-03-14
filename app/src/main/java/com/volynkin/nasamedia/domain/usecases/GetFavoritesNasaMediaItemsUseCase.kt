package com.volynkin.nasamedia.domain.usecases

import com.volynkin.nasamedia.domain.repositories.NASAMediaItemsRepository
import javax.inject.Inject

class GetFavoritesNasaMediaItemsUseCase @Inject constructor(private val NASAMediaItemRepository: NASAMediaItemsRepository) {
    suspend operator fun invoke() =
        NASAMediaItemRepository.getFavoritesNASAMediaItem()
}