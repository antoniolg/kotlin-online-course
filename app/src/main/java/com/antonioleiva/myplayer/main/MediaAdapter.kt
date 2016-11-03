package com.antonioleiva.myplayer.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.antonioleiva.myplayer.R
import com.antonioleiva.myplayer.inflate
import com.antonioleiva.myplayer.loadUrl
import com.antonioleiva.myplayer.model.MediaItem
import kotlinx.android.synthetic.main.view_media_item.view.*
import kotlin.properties.Delegates

typealias Listener = (MediaItem) -> Unit

class MediaAdapter(data: List<MediaItem> = emptyList(), private val listener: Listener) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var data by Delegates.observable(data) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_media_item), listener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View, private val listener: Listener) :
        RecyclerView.ViewHolder(view) {

        fun bind(item: MediaItem) = with(itemView) {
            media_title.text = item.title
            media_thumb.loadUrl(item.thumbUrl)
            setOnClickListener { listener(item) }

            media_video_indicator.visibility = when (item.type) {
                MediaItem.Type.PHOTO -> View.GONE
                MediaItem.Type.VIDEO -> View.VISIBLE
            }
        }
    }
}