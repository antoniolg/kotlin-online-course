package com.antonioleiva.myplayer.model

import com.antonioleiva.myplayer.model.MediaItem.Type
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

interface Provider {
    fun dataAsync(f: (List<MediaItem>) -> Unit)
}

object MediaProvider : Provider {
    private const val thumbBase = "http://lorempixel.com/400/400"
    private val rnd = Random(1)

    private var data = emptyList<MediaItem>()

    private fun randomType() = rnd.nextInt(2).let { if (it == 0) Type.PHOTO else Type.VIDEO }

    override fun dataAsync(f: (List<MediaItem>) -> Unit) {
        doAsync {
            if (data.isEmpty()) data = dataSync("cats")
            uiThread { f(data) }
        }
    }

    private fun dataSync(dataType: String): List<MediaItem> {
        Thread.sleep(5000)
        return (1L..10L).map {
            MediaItem(
                it,
                "Title $it",
                "$thumbBase/$dataType/$it",
                randomType()
            )
        }
    }
}