package com.mordva.movieScreen.domain.model

import com.mordva.model.person.Person

internal data class UnionParams(
    val personsList: List<Person>,
    val personsScreenObjectList: List<PersonMovieScreenObject>
)
