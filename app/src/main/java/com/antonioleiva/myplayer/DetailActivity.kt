package com.antonioleiva.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    companion object {
        const val EXTRA_ID = "DetailActivity:extraId"
    }

    private val presenter = DetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter.onCreate(intent.getLongExtra(EXTRA_ID, -1))
    }

    override fun setTitle(title: String) = run { supportActionBar?.title = title }

    override fun setImage(url: String) = detail_thumb.loadUrl(url)

    override fun setDetailIndicatorVisible(visible: Boolean) = with(detail_video_indicator) {
        if (visible) {
            show()
        } else {
            hide()
        }
    }
}
