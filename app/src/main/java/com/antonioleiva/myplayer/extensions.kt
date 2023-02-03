package com.antonioleiva.myplayer

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun MediaAdapter.ViewHolder.toast(message: String) {
    itemView.context.toast(message)
}