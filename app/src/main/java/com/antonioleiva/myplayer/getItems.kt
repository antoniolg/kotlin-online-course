package com.antonioleiva.myplayer

fun getItems(): List<MediaItem> = listOf(
    MediaItem("Title 1", "https://placekitten.com/200/200?image=1", MediaItem.Type.PHOTO),
    MediaItem("Title 2", "https://placekitten.com/200/200?image=2", MediaItem.Type.PHOTO),
    MediaItem("Title 3", "https://placekitten.com/200/200?image=3", MediaItem.Type.VIDEO),
    MediaItem("Title 4", "https://placekitten.com/200/200?image=4", MediaItem.Type.PHOTO),
    MediaItem("Title 5", "https://placekitten.com/200/200?image=5", MediaItem.Type.PHOTO),
    MediaItem("Title 6", "https://placekitten.com/200/200?image=6", MediaItem.Type.VIDEO),
    MediaItem("Title 7", "https://placekitten.com/200/200?image=7", MediaItem.Type.PHOTO),
    MediaItem("Title 8", "https://placekitten.com/200/200?image=8", MediaItem.Type.PHOTO),
    MediaItem("Title 9", "https://placekitten.com/200/200?image=9", MediaItem.Type.PHOTO),
    MediaItem("Title 10", "https://placekitten.com/200/200?image=10", MediaItem.Type.VIDEO)
)