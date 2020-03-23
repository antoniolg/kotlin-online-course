package com.antonioleiva.myplayer.ui.detail

import com.antonioleiva.myplayer.data.MediaItem.Type
import com.antonioleiva.myplayer.data.MediaProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPresenter(private val view: View, private val scope: CoroutineScope) {

    interface View {
        fun setTitle(title: String)
        fun setImage(url: String)
        fun setDetailIndicatorVisible(visible: Boolean)
    }

    fun onCreate(itemId: Int) {
        scope.launch {
            val items = withContext(Dispatchers.IO) { MediaProvider.getItems() }
            val item = items.find { it.id == itemId }

            item?.let {
                view.setTitle(item.title)
                view.setImage(item.url)
                view.setDetailIndicatorVisible(item.type == Type.VIDEO)
            }
        }
    }
}