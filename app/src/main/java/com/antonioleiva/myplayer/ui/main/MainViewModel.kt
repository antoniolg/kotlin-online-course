package com.antonioleiva.myplayer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonioleiva.myplayer.data.Filter
import com.antonioleiva.myplayer.data.MediaItem
import com.antonioleiva.myplayer.data.MediaProvider
import com.antonioleiva.myplayer.data.MediaProviderImpl
import com.antonioleiva.myplayer.ui.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val mediaProvider: MediaProvider = MediaProviderImpl,
    private val ioDispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {

    private val _items = MutableLiveData<List<MediaItem>>()
    val items: LiveData<List<MediaItem>> get() = _items

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _navigateToDetail = MutableLiveData<Event<Int>>()
    val navigateToDetail: LiveData<Event<Int>> get() = _navigateToDetail

    fun onFilterSelected(filter: Filter) {
        viewModelScope.launch {
            _progressVisible.value = true
            _items.value = withContext(ioDispatcher) { getFilteredItems(filter) }
            _progressVisible.value = false
        }
    }

    private fun getFilteredItems(filter: Filter): List<MediaItem> {
        return mediaProvider.getItems().let { media ->
            when (filter) {
                Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }
    }

    fun onItemClicked(item: MediaItem) {
        _navigateToDetail.value = Event(item.id)
    }
}