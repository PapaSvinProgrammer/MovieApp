package com.mordva.ui.widget.other

import com.mordva.model.SearchItem
import com.mordva.model.movie.Movie
import com.mordva.model.totalValue.ReleaseYears
import com.mordva.util.convert.ConvertData

fun Movie.toSearchItem(): SearchItem {
    return SearchItem(
        id = this.id,
        isMovie = true,
        name = this.name ?: "",
        alternativeName = ConvertData.getAlternativeNameForMovie(this),
        year = this.year ?: 0,
        releaseYears = this.releaseYears.firstOrNull() ?: ReleaseYears(null, null),
        poster = this.poster?.url ?: "",
        rating = this.rating?.kp ?: 0f
    )
}