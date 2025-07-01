package com.example.model.person

import com.example.model.movie.ShortMovie

data class NominationAward(
    val nomination: Nomination?,
    val winning: Boolean?,
    val personId: Int?,
    val movie: ShortMovie?
)
