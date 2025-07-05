package com.example.room.external

import com.example.model.History
import com.example.room.internal.HistoryEntity

fun History.toEntity(): HistoryEntity = HistoryEntity(
    id = id,
    movieId = movieId,
    name = name,
    alternativeName = alternativeName,
    year = year,
    start = start,
    end = end,
    poster = poster,
    isMovie = isMovie
)

fun HistoryEntity.toDomain(): History = History(
    id = id,
    movieId = movieId,
    name = name,
    alternativeName = alternativeName,
    year = year,
    start = start,
    end = end,
    poster = poster,
    isMovie = isMovie
)