package com.mordva.search.presentation.searchScreen.util

import com.mordva.model.local.History
import com.mordva.model.SearchItem
import com.mordva.model.movie.Movie
import com.mordva.model.person.Person
import com.mordva.model.totalValue.ReleaseYears
import com.mordva.util.convert.ConvertData

fun SearchItem.toHistory(): History {
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

fun Person.toSearchItem(): SearchItem {
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
fun List<Movie>.toSearchItemList(): List<SearchItem> {
    val res = ArrayList<SearchItem>()

    this.forEach {
        res.add(it.toSearchItem())
    }

    return res
}

@JvmName(name = "personToSearchItemList")
fun List<Person>.toSearchItemList(): List<SearchItem> {
    val res = ArrayList<SearchItem>()

    this.forEach {
        res.add(it.toSearchItem())
    }

    return res
}

fun History.toSearchItem(): SearchItem {
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