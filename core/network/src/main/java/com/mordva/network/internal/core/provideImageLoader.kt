package com.mordva.network.internal.core

import android.content.Context
import coil3.ImageLoader
import coil3.request.crossfade

internal fun provideImageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .crossfade(true)
        .build()
}