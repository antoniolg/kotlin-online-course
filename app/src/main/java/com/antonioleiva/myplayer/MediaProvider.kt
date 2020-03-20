package com.antonioleiva.myplayer

import com.antonioleiva.myplayer.MediaItem.Type

object MediaProvider {
    fun getItems() = (1..10).map {
        MediaItem(
            "Title $it",
            "https://placekitten.com/200/200?image=$it",
            if (it % 3 == 0) Type.VIDEO else Type.PHOTO
        )
    }
}