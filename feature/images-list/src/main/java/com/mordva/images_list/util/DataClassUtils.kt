package com.mordva.images_list.util

import com.mordva.model.image.Poster
import com.mordva.ui.uiState.ImageUIState

fun ImageUIState.getData(): List<Poster> {
    return (this as? ImageUIState.Success)?.data ?: listOf()
}