package com.antonioleiva.myplayer.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.antonioleiva.myplayer.R
import com.antonioleiva.myplayer.data.Filter
import com.antonioleiva.myplayer.data.MediaItem.Type
import com.antonioleiva.myplayer.databinding.ActivityMainBinding
import com.antonioleiva.myplayer.ui.detail.DetailActivity
import com.antonioleiva.myplayer.ui.setVisible
import com.antonioleiva.myplayer.ui.startActivity

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MediaAdapter { viewModel.onItemClicked(it) } }
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get<MainViewModel>().apply {
            items.observe(this@MainActivity, Observer { adapter.items = it })
            progressVisible.observe(this@MainActivity, Observer { binding.progress.setVisible(it) })
            navigateToDetail.observe(this@MainActivity, Observer { navigateToDetail(it) })
        }

        binding.recycler.adapter = adapter
        viewModel.onFilterSelected(Filter.None)
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

        viewModel.onFilterSelected(filter)
        return true
    }

    private fun navigateToDetail(id: Int) {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to id)
    }
}