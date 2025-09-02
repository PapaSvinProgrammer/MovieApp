package com.mordva.movieScreen.utils

import com.mordva.model.category.WatchabilityItem
import com.mordva.model.image.Poster
import com.mordva.model.local.RatedMovie
import com.mordva.model.movie.Movie
import com.mordva.model.movie.Watchability
import com.mordva.model.person.PersonMovie
import com.mordva.movieScreen.domain.model.PersonMovieScreenObject
import com.mordva.movieScreen.domain.model.PosterScreenObject
import com.mordva.movieScreen.domain.model.WatchabilityItemScreenObject
import com.mordva.movieScreen.domain.model.WatchabilityScreenObject

fun Watchability.toScreenObject() = WatchabilityScreenObject(items.map { it.toDto() })
internal fun WatchabilityItem.toDto() = WatchabilityItemScreenObject(name, logo?.toDto(), url)
internal fun Poster.toDto() = PosterScreenObject(url, previewUrl)

fun List<PersonMovie>.toScreenObject() = map { it.toScreenObject() }
internal fun PersonMovie.toScreenObject() = PersonMovieScreenObject(
    id = id,
    description = description,
    enProfession = enProfession,
    profession = profession
)

internal fun Movie.toRatedMovie(rating: Int): RatedMovie {
    return RatedMovie(
        movieId = id,
        name = name ?: "",
        poster = this.poster?.url ?: "",
        rating = rating
    )
}