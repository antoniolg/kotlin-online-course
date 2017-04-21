package com.antonioleiva.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val adapter = MediaAdapter { itemClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = adapter
        progress.show()

        loadContent()
    }

    private fun loadContent(filter: Filter = Filter.None) = GlobalScope.launch(Dispatchers.Main) {
        val cats = async(Dispatchers.IO) { MediaProvider.dataSync("cats") }
        val nature = async(Dispatchers.IO) { MediaProvider.dataSync("nature") }
        updateData(cats.await() + nature.await(), filter)
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
            loadContent(filter)
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
