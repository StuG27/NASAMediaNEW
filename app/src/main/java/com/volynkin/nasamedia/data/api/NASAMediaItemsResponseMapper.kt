package com.volynkin.nasamedia.data.api

import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import com.volynkin.nasamedia.domain.entities.NASAMediaItemInfo

class NASAMediaItemsResponseMapper {
    fun toNASAMediaItemList(response: NASAApiResponse): List<NASAMediaItem> {
        val list = mutableListOf<NASAMediaItem>()
        response.collection.items.forEach {
            list.add(
                NASAMediaItem(
                    id = it.data.id,
                    title = it.data.title,
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
        return list
    }
}