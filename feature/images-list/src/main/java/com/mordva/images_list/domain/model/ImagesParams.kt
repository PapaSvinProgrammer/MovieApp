package com.mordva.images_list.domain.model

import com.mordva.model.image.ImageType

internal data class ImagesParams(
    val movieId: Int,
    val page: Int = 1,
    val types: Set<ImageType> = setOf(ImageType.ALL),
)