package com.volynkin.nasamedia.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteNASAMediaItem(
    @PrimaryKey
    val id: String,
    val title: String,
    val contentLink: String,
    val preview: String,
    val description: String,
    val date: String,
    val type: String,
    val keywords: String
)