package com.antonioleiva.myplayer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonioleiva.myplayer.data.MediaItem
import com.antonioleiva.myplayer.data.MediaProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DetailViewModel(
    private val mediaProvider: MediaProvider,
    private val ioDispatcher: CoroutineContext
) : ViewModel() {

    private val _item = MutableLiveData<MediaItem>()
    val item: LiveData<MediaItem> get() = _item

    fun onCreate(itemId: Int) {
        viewModelScope.launch {
            val items = withContext(ioDispatcher) { mediaProvider.getItems() }
            val item = items.find { it.id == itemId }

            item?.let {
                _item.value = item
            }
        }
    }
}