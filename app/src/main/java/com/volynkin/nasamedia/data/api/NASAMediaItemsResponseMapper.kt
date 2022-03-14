package com.volynkin.nasamedia.data.api

import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import com.volynkin.nasamedia.domain.entities.NASAMediaItemInfo

class NASAMediaItemsResponseMapper {
    fun toNASAMediaItemList(response: NASAApiResponse): List<NASAMediaItem> {
        return listOf(
            NASAMediaItem(
                id = "",
                title = "",
                NASAMediaItemInfo(
                    contentLink = "",
                    preview = "",
                    description = "",
                    date = "",
                    type = "",
                    keywords = ""
                )
            )
        )
    }
}