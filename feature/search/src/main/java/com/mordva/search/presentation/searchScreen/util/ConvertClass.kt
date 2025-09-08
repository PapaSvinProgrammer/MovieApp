package com.mordva.search.presentation.searchScreen.util

import com.mordva.model.SearchItem
import com.mordva.model.local.History
import com.mordva.model.movie.Movie
import com.mordva.model.person.Person
import com.mordva.model.totalValue.ReleaseYears
import com.mordva.ui.widget.other.toSearchItem

internal fun SearchItem.toHistory(): History {
    return History(
        movieId = this.id,
        name = this.name,
        alternativeName = this.alternativeName,
        year = this.year,
        start = this.releaseYears.start,
        end = this.releaseYears.end,
        poster = this.poster,
        isMovie = this.isMovie
    )
}

internal fun Person.toSearchItem(): SearchItem {
    return SearchItem(
        id = this.id,
        isMovie = false,
        name = this.name ?: "",
        alternativeName = this.enName ?: "",
        year = this.age ?: 0,
        releaseYears = ReleaseYears(null, null),
        poster = this.photo ?: "",
        rating = 0f
    )
}

@JvmName(name = "movieToSearchItemList")
internal fun List<Movie>.toSearchItemList(): List<SearchItem> {
    val res = ArrayList<SearchItem>()

    this.forEach {
        res.add(it.toSearchItem())
    }

    return res
}

@JvmName(name = "personToSearchItemList")
internal fun List<Person>.toSearchItemList(): List<SearchItem> {
    val res = ArrayList<SearchItem>()

    this.forEach {
        res.add(it.toSearchItem())
    }

    return res
}

internal fun History.toSearchItem(): SearchItem {
    val releaseYears = ReleaseYears(
        start = this.start,
        end = this.end
    )

    return SearchItem(
        id = this.movieId,
        name = this.name,
        alternativeName = this.alternativeName,
        year = this.year,
        releaseYears = releaseYears,
        poster = this.poster,
        isMovie = this.isMovie,
        rating = 0f
    )
}