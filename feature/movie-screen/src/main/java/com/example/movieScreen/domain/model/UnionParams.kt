package com.example.movieScreen.domain.model

import com.example.model.person.Person
import com.example.navigationroute.model.PersonMovieScreenObject

internal data class UnionParams(
    val personsList: List<Person>,
    val personsScreenObjectList: List<PersonMovieScreenObject>
)
