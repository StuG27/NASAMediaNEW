package com.volynkin.nasamedia.data.api

import com.squareup.moshi.Json

class NASAApiResponse(
    val collection: NASACollection
)

data class NASACollection(
    val items: List<Item>
)

data class Item(
    val data: Data
)

data class Data(
    val description: String,
    @field:Json(name = "nasa_id")
    val id: String,
    val title: String
)