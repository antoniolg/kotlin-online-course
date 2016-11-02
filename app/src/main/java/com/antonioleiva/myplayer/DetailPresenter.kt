package com.antonioleiva.myplayer

class DetailPresenter(private val view: View) {

    interface View {
        fun setTitle(title: String)
        fun setImage(url: String)
        fun setDetailIndicatorVisible(visible: Boolean)
    }

    fun onCreate(itemId: Long) = MediaProvider.dataAsync { media ->

        val item = media.find { it.id == itemId }

        item?.let {
            view.setTitle(item.title)
            view.setImage(item.thumbUrl)
            view.setDetailIndicatorVisible(item.type == MediaItem.Type.VIDEO)
        }
    }
}