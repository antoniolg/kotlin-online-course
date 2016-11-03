package com.antonioleiva.myplayer.model

sealed class Filter {
    object None : Filter()
    class ByMediaType(val type: MediaItem.Type) : Filter()
}