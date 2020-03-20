package com.antonioleiva.myplayer

data class MediaItem(val id: Int, val title: String, val url: String, val type: Type) {
    enum class Type { PHOTO, VIDEO }
}