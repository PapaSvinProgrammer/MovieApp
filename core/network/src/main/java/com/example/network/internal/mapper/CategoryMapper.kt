package com.example.network.internal.mapper

import com.example.model.category.ItemName
import com.example.model.category.WatchabilityItem
import com.example.network.internal.model.category.WatchabilityItemDto
import com.example.network.internal.model.category.ItemNameDto

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