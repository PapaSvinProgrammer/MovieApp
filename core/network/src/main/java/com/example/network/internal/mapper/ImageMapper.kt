package com.example.network.internal.mapper

import com.example.model.image.Collection
import com.example.network.internal.model.image.CollectionDto
import com.example.model.image.Poster
import com.example.network.internal.model.image.PosterDto

internal fun CollectionDto.toDomain(): Collection {
    return Collection(
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