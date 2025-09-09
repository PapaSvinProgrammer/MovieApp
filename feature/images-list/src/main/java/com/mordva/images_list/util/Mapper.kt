package com.mordva.images_list.util

import android.content.Context
import com.example.movieapp.images_list.R
import com.mordva.model.image.ImageType
import com.mordva.ui.widget.component.customDropDownList.DropDownItem

internal fun ImageType.toDropDownItem(context: Context): DropDownItem {
    val text = when (this) {
        ImageType.ALL -> context.getString(R.string.all)
        ImageType.BACKDROP -> context.getString(R.string.backdrop)
        ImageType.COVER -> context.getString(R.string.cover)
        ImageType.FRAME -> context.getString(R.string.frame)
        ImageType.PROMO -> context.getString(R.string.promo)
        ImageType.SCREENSHOT -> context.getString(R.string.screenshot)
        ImageType.SHOOTING -> context.getString(R.string.shooting)
        ImageType.STILL -> context.getString(R.string.still)
        ImageType.WALLPAPER -> context.getString(R.string.wallpaper)
    }

    return DropDownItem(text = text)
}

internal fun Set<ImageType>.toDropDownItem(context: Context) = map { it.toDropDownItem(context) }

internal fun DropDownItem.toImageType(context: Context): ImageType? {
    return when (this.text) {
        context.getString(R.string.all) -> ImageType.ALL
        context.getString(R.string.backdrop) -> ImageType.BACKDROP
        context.getString(R.string.cover) -> ImageType.COVER
        context.getString(R.string.frame) -> ImageType.FRAME
        context.getString(R.string.promo) -> ImageType.PROMO
        context.getString(R.string.screenshot) -> ImageType.SCREENSHOT
        context.getString(R.string.shooting) -> ImageType.SHOOTING
        context.getString(R.string.still) -> ImageType.STILL
        context.getString(R.string.wallpaper) -> ImageType.WALLPAPER
        else -> null
    }
}
