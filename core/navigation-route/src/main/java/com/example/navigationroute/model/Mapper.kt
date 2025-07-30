package com.example.navigationroute.model

import com.example.model.category.WatchabilityItem
import com.example.model.image.Poster
import com.example.model.movie.Watchability

fun Watchability.toScreenObject() = WatchabilityScreenObject(items.map { it.toDto() })
internal fun WatchabilityItem.toDto() = WatchabilityItemScreenObject(name, logo?.toDto(), url)
internal fun Poster.toDto() = PosterScreenObject(url, previewUrl)