package com.volynkin.nasamedia.domain.entities

data class NASAMediaItem(
    val id: String,
    val title: String,
    val info: NASAMediaItemInfo
)

data class NASAMediaItemInfo(
    val contentLink: String,
    val preview: String,
    val description: String,
    val date: String,
    val type: String,
    val keywords: String
)
