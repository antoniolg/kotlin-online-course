package com.antonioleiva.myplayer.detail

import com.antonioleiva.myplayer.model.MediaItem
import com.antonioleiva.myplayer.model.Provider

class DetailPresenter(private val view: View, private val provider: Provider) {

    interface View {
        fun setTitle(title: String)
        fun setImage(url: String)
        fun setDetailIndicatorVisible(visible: Boolean)
    }

    fun onCreate(itemId: Long) = provider.dataAsync { media ->

        val item = media.find { it.id == itemId }

        item?.let {
            view.setTitle(item.title)
            view.setImage(item.thumbUrl)
            view.setDetailIndicatorVisible(item.type == MediaItem.Type.VIDEO)
        }
    }
}