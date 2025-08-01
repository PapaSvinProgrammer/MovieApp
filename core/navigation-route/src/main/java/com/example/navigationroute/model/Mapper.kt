package com.example.navigationroute.model

import com.example.model.category.WatchabilityItem
import com.example.model.image.Poster
import com.example.model.movie.Watchability
import com.example.model.person.PersonMovie

fun Watchability.toScreenObject() = WatchabilityScreenObject(items.map { it.toDto() })
internal fun WatchabilityItem.toDto() = WatchabilityItemScreenObject(name, logo?.toDto(), url)
internal fun Poster.toDto() = PosterScreenObject(url, previewUrl)

fun List<PersonMovie>.toScreenObject() = this.map { it.toScreenObject() }
internal fun PersonMovie.toScreenObject() = PersonMovieScreenObject(
    id = id,
    description = description,
    enProfession = enProfession,
    profession = profession
)