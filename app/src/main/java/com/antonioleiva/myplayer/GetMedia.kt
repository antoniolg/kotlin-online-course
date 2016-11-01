package com.antonioleiva.myplayer

private const val thumbBase = "http://lorempixel.com/400/400/cats/"

fun getMedia() = listOf(
        MediaItem("Title 1", "${thumbBase}1", MediaItem.Type.PHOTO),
        MediaItem("Title 2", "${thumbBase}2", MediaItem.Type.PHOTO),
        MediaItem("Title 3", "${thumbBase}3", MediaItem.Type.VIDEO),
        MediaItem("Title 4", "${thumbBase}4", MediaItem.Type.PHOTO),
        MediaItem("Title 5", "${thumbBase}5", MediaItem.Type.PHOTO),
        MediaItem("Title 6", "${thumbBase}6", MediaItem.Type.VIDEO),
        MediaItem("Title 7", "${thumbBase}7", MediaItem.Type.VIDEO),
        MediaItem("Title 8", "${thumbBase}8", MediaItem.Type.PHOTO),
        MediaItem("Title 9", "${thumbBase}9", MediaItem.Type.PHOTO),
        MediaItem("Title 10", "${thumbBase}10", MediaItem.Type.VIDEO)
)