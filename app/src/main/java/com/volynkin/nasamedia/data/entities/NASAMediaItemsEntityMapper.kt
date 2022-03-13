package com.volynkin.nasamedia.data.entities

import com.volynkin.nasamedia.domain.entities.NASAMediaItem
import com.volynkin.nasamedia.domain.entities.NASAMediaItemInfo

class NASAMediaItemsEntityMapper {
    fun toRemoteNASAMediaItemEntity(NASAMediaItem: NASAMediaItem): RemoteNASAMediaItem {
        return RemoteNASAMediaItem(
            id = NASAMediaItem.id,
            title = NASAMediaItem.title,
            contentLink = NASAMediaItem.info.contentLink,
            preview = NASAMediaItem.info.preview,
            description = NASAMediaItem.info.description,
            date = NASAMediaItem.info.date,
            type = NASAMediaItem.info.type,
            keywords = NASAMediaItem.info.keywords
        )
    }

    fun toNASAMediaItems(remoteNASAMediaItem: RemoteNASAMediaItem): NASAMediaItem {
        return NASAMediaItem(
            id = remoteNASAMediaItem.id,
            title = remoteNASAMediaItem.title,
            NASAMediaItemInfo(
                contentLink = remoteNASAMediaItem.contentLink,
                preview = remoteNASAMediaItem.preview,
                description = remoteNASAMediaItem.description,
                date = remoteNASAMediaItem.date,
                type = remoteNASAMediaItem.type,
                keywords = remoteNASAMediaItem.keywords
            )
        )
    }
}