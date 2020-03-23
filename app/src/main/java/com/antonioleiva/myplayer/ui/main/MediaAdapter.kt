package com.antonioleiva.myplayer.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antonioleiva.myplayer.data.MediaItem
import com.antonioleiva.myplayer.R
import com.antonioleiva.myplayer.databinding.ViewMediaItemBinding
import com.antonioleiva.myplayer.ui.inflate
import com.antonioleiva.myplayer.ui.loadUrl
import kotlin.properties.Delegates

private typealias MediaListener = (MediaItem) -> Unit

class MediaAdapter(items: List<MediaItem> = emptyList(), private val listener: MediaListener) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items by Delegates.observable(items) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = parent.inflate(R.layout.view_media_item, false)
        return ViewHolder(
            v,
            listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View, private val listener: MediaListener) :
        RecyclerView.ViewHolder(view) {

        private val binding = ViewMediaItemBinding.bind(view)

        fun bind(mediaItem: MediaItem) {
            with(binding) {
                mediaTitle.text = mediaItem.title
                mediaThumb.loadUrl(mediaItem.url)
                root.setOnClickListener { listener(mediaItem) }

                mediaVideoIndicator.visibility = when (mediaItem.type) {
                    MediaItem.Type.PHOTO -> View.GONE
                    MediaItem.Type.VIDEO -> View.VISIBLE
                }
            }
        }
    }
}