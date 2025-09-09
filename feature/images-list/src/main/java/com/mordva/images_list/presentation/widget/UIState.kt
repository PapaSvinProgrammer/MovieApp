package com.mordva.images_list.presentation.widget

import com.mordva.model.image.ImageType
import com.mordva.ui.uiState.ImageUIState

internal data class UIState(
    val page: Int = 1,
    val imagesState: ImageUIState = ImageUIState.Loading,
    val imageTypes: Set<ImageType> = setOf(ImageType.ALL)
)