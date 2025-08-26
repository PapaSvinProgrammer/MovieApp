package com.mordva.network.internal.mapper

import com.mordva.model.image.CollectionMovie
import com.mordva.network.internal.model.image.CollectionDto
import com.mordva.model.image.Poster
import com.mordva.network.internal.model.image.PosterDto

internal fun CollectionDto.toDomain(): CollectionMovie {
    return CollectionMovie(
        slug = this.slug,
        category = this.category,
        cover = this.cover?.toDomain(),
        moviesCount = this.moviesCount,
        name = this.name
    )
}

internal fun PosterDto.toDomain(): Poster {
    return Poster(
        url = this.url,
        previewUrl = this.previewUrl
    )
}