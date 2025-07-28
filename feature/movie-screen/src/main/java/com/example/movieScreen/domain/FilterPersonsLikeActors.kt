package com.example.movieScreen.domain

import com.example.model.person.PersonMovie
import javax.inject.Inject

internal class FilterPersonsLikeActors @Inject constructor() {
    fun execute(list: List<PersonMovie>): List<PersonMovie> {
        return list.filter { it.enProfession == "actor" }
    }
}