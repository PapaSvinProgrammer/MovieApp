package com.mordva.network.internal.mapper

import com.mordva.model.category.ItemName
import com.mordva.model.category.WatchabilityItem
import com.mordva.network.internal.model.category.WatchabilityItemDto
import com.mordva.network.internal.model.category.ItemNameDto

internal fun ItemNameDto.toDomain(): ItemName {
    return ItemName(
        name = this.name,
        slug = this.slug
    )
}

internal fun WatchabilityItemDto.toDomain(): WatchabilityItem {
    return WatchabilityItem(
        name = this.name,
        logo = this.logo?.toDomain(),
        url = this.url
    )
}