package com.udacity.asteroidradar

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson

data class PictureOfDay(@Json(name = "media_type") val mediaType: MediaType, val title: String,
                        val url: String) {
    val isImage : Boolean = mediaType == MediaType.IMAGE
}

enum class MediaType {VIDEO, IMAGE, UNKNOWN}

class MediaTypeAdapter {
    @ToJson fun toJson(type: MediaType) : String {
        return when (type) {
            MediaType.VIDEO -> "video"
            MediaType.IMAGE -> "image"
            else -> "unknown"
        }
    }

    @FromJson
    fun fromJson(type: String) : MediaType {
        return when (type) {
            "video" -> MediaType.VIDEO
            "image" -> MediaType.IMAGE
            else -> MediaType.UNKNOWN
        }

    }
}