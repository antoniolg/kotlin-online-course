package com.antonioleiva.myplayer.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.antonioleiva.myplayer.R
import com.antonioleiva.myplayer.data.Filter
import com.antonioleiva.myplayer.data.MediaItem
import com.antonioleiva.myplayer.data.MediaItem.Type
import com.antonioleiva.myplayer.databinding.ActivityMainBinding
import com.antonioleiva.myplayer.ui.detail.DetailActivity
import com.antonioleiva.myplayer.ui.setVisible
import com.antonioleiva.myplayer.ui.startActivity

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val adapter by lazy { MediaAdapter { presenter.onItemClicked(it) } }
    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this, lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
        presenter.onFilterSelected(Filter.None)
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

        presenter.onFilterSelected(filter)
        return true
    }

    override fun setProgressVisible(visible: Boolean) {
        binding.progress.setVisible(visible)
    }

    override fun updateItems(items: List<MediaItem>) {
        adapter.items = items
    }

    override fun navigateToDetail(id: Int) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to id)
    }
}