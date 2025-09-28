package com.mordva.images_list.util

import com.mordva.model.image.Poster
import com.mordva.ui.uiState.ImageUIState

internal fun ImageUIState.getData(): List<Poster> {
    return (this as? ImageUIState.Success)?.data ?: listOf()
}

internal fun toAspectRatio(height: Int?, width: Int?): Float {
    val normalWidth = width ?: 0
    val normalHeight = height ?: 0
    val aspectRatio = normalWidth.toFloat() / normalHeight.toFloat()
    return aspectRatio
}

internal fun isLongImage(height: Int?, width: Int?): Boolean {
    val normalWidth = width ?: 0
    val normalHeight = height ?: 0
    return normalWidth > normalHeight * 2
}