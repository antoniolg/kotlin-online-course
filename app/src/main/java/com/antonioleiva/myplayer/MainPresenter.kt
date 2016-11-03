package com.antonioleiva.myplayer

class MainPresenter(private val view: View, private val provider: Provider = MediaProvider) {

    interface View {
        fun updateData(media: List<MediaItem>)
        fun showProgress()
        fun hideProgress()
        fun navigateTo(id: Long)
    }

    fun onCreate() {
        filterClicked(Filter.None)
    }

    fun filterClicked(filter: Filter) {
        view.showProgress()
        provider.dataAsync { media ->

            val result = when (filter) {
                Filter.None -> media
                is Filter.ByMediaType -> media.filter { it.type == filter.type }
            }

            view.updateData(result)
            view.hideProgress()
        }
    }

    fun itemClicked(item: MediaItem) {
        view.navigateTo(item.id)
    }

}