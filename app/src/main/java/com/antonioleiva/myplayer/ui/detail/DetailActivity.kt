package com.antonioleiva.myplayer.ui.detail

import android.os.Bundle
import com.antonioleiva.myplayer.data.MediaItem.Type
import com.antonioleiva.myplayer.databinding.ActivityDetailBinding
import com.antonioleiva.myplayer.ui.loadUrl
import com.antonioleiva.myplayer.ui.observe
import com.antonioleiva.myplayer.ui.setVisible
import org.koin.android.scope.ScopeActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : ScopeActivity() {

    companion object {
        const val EXTRA_ID = "DetailActivity:extraId"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(viewModel) {
            observe(item) {
                supportActionBar?.title = it.title
                binding.detailThumb.loadUrl(it.url)
                binding.detailVideoIndicator.setVisible(it.type == Type.VIDEO)
            }
        }

        viewModel.onCreate(intent.getIntExtra(EXTRA_ID, -1))
    }

}
