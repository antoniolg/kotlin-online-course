package com.antonioleiva.myplayer

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MediaAdapter(private val data: List<MediaItem>) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.view_media_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.media_title)
        private val thumb: ImageView = view.findViewById(R.id.media_thumb)
        private val videoIndicator: View = view.findViewById(R.id.media_video_indicator)

        fun bind(item: MediaItem) {
            title.text = item.title
            thumb.loadUrl(item.thumbUrl)
            itemView.setOnClickListener { toast(item.title) }

            videoIndicator.visibility = when (item.type) {
                MediaItem.Type.PHOTO -> View.GONE
                MediaItem.Type.VIDEO -> View.VISIBLE
            }
        }

    }
}