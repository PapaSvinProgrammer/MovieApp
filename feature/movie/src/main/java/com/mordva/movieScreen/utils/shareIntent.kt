package com.mordva.movieScreen.utils

import android.content.Context
import com.mordva.model.movie.Movie

internal fun Context.shareMovieIntent(movie: Movie) {
//    val share = Intent.createChooser(Intent().apply {
//        action = Intent.ACTION_SEND
//        putExtra(Intent.EXTRA_TEXT, "https://developer.android.com/training/sharing/")
//        putExtra(Intent.EXTRA_TITLE, "Introducing content previews")
//
//        //data = android.net.Uri.parse(movie.poster?.url ?: "")
//        type = "text/plain"
//        //flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//    }, null)
//
//    share.clipData = ClipData.newRawUri()
//
//    startActivity(share)
}