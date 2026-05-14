package com.example.kavyakanaja.utils

import android.content.Context
import android.media.MediaPlayer

object AudioPlayer {

    private var player: MediaPlayer? = null

    fun play(context: Context, fileName: String) {

        val resId = context.resources.getIdentifier(
            fileName,
            "raw",
            context.packageName
        )

        player?.release()
        player = MediaPlayer.create(context, resId)
        player?.start()
    }
}