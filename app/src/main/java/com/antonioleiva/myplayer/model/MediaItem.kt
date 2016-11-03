package com.antonioleiva.myplayer.model

data class MediaItem(val id: Long, val title: String, val thumbUrl: String, val type: Type) {
    enum class Type { PHOTO, VIDEO }
}