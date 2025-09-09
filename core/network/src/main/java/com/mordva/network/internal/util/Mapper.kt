package com.mordva.network.internal.util

import com.mordva.model.image.ImageType

internal fun ImageType.toKtorString(): String {
    return when (this) {
        ImageType.ALL -> ""
        ImageType.BACKDROP -> "backdrops"
        ImageType.COVER -> "cover"
        ImageType.FRAME -> "frame"
        ImageType.PROMO -> "promo"
        ImageType.SCREENSHOT -> "screenshot"
        ImageType.SHOOTING -> "shooting"
        ImageType.STILL -> "still"
        ImageType.WALLPAPER -> "wallpaper"
    }
}