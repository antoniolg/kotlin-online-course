package com.antonioleiva.myplayer

import com.antonioleiva.myplayer.MediaItem.Type
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

object MediaProvider {
    private const val thumbBase = "http://lorempixel.com/400/400/cats/"
    private val rnd = Random(1)

    private var data = emptyList<MediaItem>()

    private fun randomType() = rnd.nextInt(2).let { if (it == 0) Type.PHOTO else Type.VIDEO }

    fun dataAsync(f: (List<MediaItem>) -> Unit) = doAsync {
        if (data.isEmpty()) {
            Thread.sleep(5000)
            data = (1..10).map { MediaItem("Title $it", "$thumbBase$it", randomType()) }
        }

        uiThread {
            f(data)
        }
    }
}