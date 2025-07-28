package com.example.room.internal.utils

import com.example.model.History
import com.example.room.internal.history.HistoryEntity

internal fun History.toEntity(): HistoryEntity = HistoryEntity(
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

internal fun HistoryEntity.toDomain(): History = History(
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