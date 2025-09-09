package com.mordva.images_list.util

import android.content.Context
import com.example.movieapp.images_list.R
import com.mordva.ui.widget.component.customDropDownList.DropDownItem

fun imageTypeDropDownItems(context: Context): List<DropDownItem> {
    val all = DropDownItem(text = context.getString(R.string.all))
    val backdrop = DropDownItem(text = context.getString(R.string.backdrop))
    val cover = DropDownItem(text = context.getString(R.string.cover))
    val frame = DropDownItem(text = context.getString(R.string.frame))
    val promo = DropDownItem(text = context.getString(R.string.promo))
    val screenshot = DropDownItem(text = context.getString(R.string.screenshot))
    val shooting = DropDownItem(text = context.getString(R.string.shooting))
    val still = DropDownItem(text = context.getString(R.string.still))
    val wallpaper = DropDownItem(text = context.getString(R.string.wallpaper))

    return listOf(
        all,
        backdrop,
        cover,
        frame,
        promo,
        screenshot,
        shooting,
        still,
        wallpaper
    )
}
