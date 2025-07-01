package com.example.model

import com.example.model.totalValue.ReleaseYears

data class SearchItem(
    val id: Int,
    val name: String,
    val alternativeName: String,
    val year: Int,
    val rating: Float,
    val releaseYears: ReleaseYears,
    val poster: String,
    val isMovie: Boolean
)