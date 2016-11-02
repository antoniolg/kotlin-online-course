package com.antonioleiva.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val adapter = MediaAdapter { itemClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = adapter
        progress.show()
        MediaProvider.dataAsync { updateData(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val filter = when (item.itemId) {
            R.id.filter_all -> Filter.None
            R.id.filter_photos -> Filter.ByMediaType(MediaItem.Type.PHOTO)
            R.id.filter_videos -> Filter.ByMediaType(MediaItem.Type.VIDEO)
            else -> null
        }

        filter?.let {
            progress.show()
            MediaProvider.dataAsync { media -> updateData(media, filter) }
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun updateData(media: List<MediaItem>, filter: Filter = Filter.None) {
        adapter.data = when (filter) {
            Filter.None -> media
            is Filter.ByMediaType -> media.filter { it.type == filter.type }
        }
        progress.hide()
    }

    private fun itemClicked(it: MediaItem) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to it.id)
    }
}
