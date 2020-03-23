package com.antonioleiva.myplayer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.antonioleiva.myplayer.MediaItem.Type
import com.antonioleiva.myplayer.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MediaAdapter { itemClicked(it) } }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
        updateItems()
    }

    private fun itemClicked(mediaItem: MediaItem) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to mediaItem.id)
    }

    private fun updateItems(filter: Filter = Filter.None) {
        lifecycleScope.launch {
            binding.progress.visibility = View.VISIBLE
            val items = withContext(Dispatchers.IO) { getFilteredItems(filter) }
            adapter.items = items
            binding.progress.visibility = View.GONE
        }
    }

    private fun getFilteredItems(filter: Filter): List<MediaItem> {
        return MediaProvider.getItems().let { media ->
            when (filter) {
                Filter.None -> media
                is Filter.ByType -> media.filter { it.type == filter.type }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val filter = when (item.itemId) {
            R.id.filter_photos -> Filter.ByType(Type.PHOTO)
            R.id.filter_videos -> Filter.ByType(Type.VIDEO)
            else -> Filter.None
        }

        updateItems(filter)
        return true
    }
}