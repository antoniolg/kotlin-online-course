package com.antonioleiva.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val adapter = MediaAdapter { itemClicked(it) }
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = adapter
        presenter.onCreate()
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
            presenter.filterClicked(filter)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun itemClicked(item: MediaItem) {
        presenter.itemClicked(item)
    }

    override fun updateData(media: List<MediaItem>) {
        adapter.data = media
    }

    override fun showProgress() = progress.show()
    override fun hideProgress() = progress.hide()
    override fun navigateTo(id: Long) = startActivity<DetailActivity>(DetailActivity.EXTRA_ID to id)
}
