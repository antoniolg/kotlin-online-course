package com.antonioleiva.myplayer.ui.main

import com.antonioleiva.myplayer.data.MediaItem
import com.antonioleiva.myplayer.data.MediaProvider

class FakeMediaProvider : MediaProvider {
    override fun getItems(): List<MediaItem> = emptyList()

}