package com.antonioleiva.myplayer.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.antonioleiva.myplayer.databinding.ActivityDetailBinding
import com.antonioleiva.myplayer.ui.loadUrl
import com.antonioleiva.myplayer.ui.setVisible

class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    companion object {
        const val EXTRA_ID = "DetailActivity:extraId"
    }

    private val presenter = DetailPresenter(this, lifecycleScope)
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onCreate(intent.getIntExtra(EXTRA_ID, -1))
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setImage(url: String) {
        binding.detailThumb.loadUrl(url)
    }

    override fun setDetailIndicatorVisible(visible: Boolean) {
        binding.detailVideoIndicator.setVisible(visible)
    }
}
