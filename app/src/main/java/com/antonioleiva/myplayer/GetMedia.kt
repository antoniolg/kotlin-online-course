package com.antonioleiva.myplayer

import com.antonioleiva.myplayer.MediaItem.Type
import java.util.*

private const val thumbBase = "http://lorempixel.com/400/400/cats/"
private val rnd = Random(1)

fun getMedia() = (1..10).map { MediaItem("Title $it", "$thumbBase$it", randomType()) }

private fun randomType() = rnd.nextInt(2).let { if(it == 0) Type.PHOTO else Type.VIDEO }