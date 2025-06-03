package com.example.movieapp.app.utils

import com.example.core.data.room.entity.HistoryEntity
import com.example.network.module.image.Poster
import com.example.network.module.movie.Movie
import com.example.network.module.totalValue.ReleaseYears

fun HistoryEntity.toMovie(): Movie {
    val release = listOf(
        ReleaseYears(
            start = this.start,
            end = this.end
        )
    )

    val poster = Poster(
        url = this.poster,
        previewUrl = null
    )

    return Movie(
        id = this.movieId,
        name = this.name,
        alternativeName = this.alternativeName,
        year = this.year,
        releaseYears = release,
        poster = poster
    )
}

fun Movie.toHistoryEntity(): HistoryEntity {
    var start = -1
    var end = -1

    if (this.releaseYears.isNotEmpty()) {
        start = this.releaseYears[0].start ?: -1
        end = this.releaseYears[0].end ?: -1
    }

    return HistoryEntity(
        movieId = this.id,
        name = this.name ?: "",
        alternativeName = ConvertData.getAlternativeNameForMovie(this),
        year = this.year ?: -1,
        start = start,
        end = end,
        poster = this.poster?.url ?: ""
    )
}