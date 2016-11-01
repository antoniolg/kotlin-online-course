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
        progress.show()
        MediaProvider.dataAsync { updateData(it, item.itemId) }
        return true
    }

    private fun updateData(media: List<MediaItem>, filterId: Int = R.id.filter_all) {
        adapter.data = when (filterId) {
            R.id.filter_all -> media
            R.id.filter_photos -> media.filter { it.type == MediaItem.Type.PHOTO }
            R.id.filter_videos -> media.filter { it.type == MediaItem.Type.VIDEO }
            else -> emptyList()
        }
        progress.hide()
    }

    private fun itemClicked(it: MediaItem) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to it.id)
    }
}
