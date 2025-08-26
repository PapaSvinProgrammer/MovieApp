package com.mordva.model.person

import com.mordva.model.movie.ShortMovie

data class NominationAward(
    val nomination: Nomination?,
    val winning: Boolean?,
    val personId: Int?,
    val movie: ShortMovie?
)
