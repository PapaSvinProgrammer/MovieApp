package com.example.network.module.person

import com.example.network.module.movie.Movie

data class NominationAward(
    val nomination: Nomination,
    val winning: Boolean,
    val personId: Int,
    val movie: Movie
)
