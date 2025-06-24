package com.example.network.model.person

import com.example.network.model.movie.ShortMovie
import kotlinx.serialization.Serializable

@Serializable
data class NominationAward(
    val nomination: Nomination?,
    val winning: Boolean?,
    val personId: Int?,
    val movie: ShortMovie?
)
