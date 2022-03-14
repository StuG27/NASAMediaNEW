package com.volynkin.nasamedia.domain.usecases

import com.volynkin.nasamedia.domain.repositories.NASAMediaItemsRepository
import javax.inject.Inject

class GetNasaMediaItemsUseCase @Inject constructor(private val NASAMediaItemRepository: NASAMediaItemsRepository) {
    suspend operator fun invoke(keyWord: String) =
        NASAMediaItemRepository.getRemoteNASAMediaItem(keyWord)
}