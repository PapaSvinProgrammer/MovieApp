package com.example.movieScreen.domain

import com.example.model.person.PersonMovie
import javax.inject.Inject

internal class FilterPersonsLikeSupport @Inject constructor() {
    fun execute(list: List<PersonMovie>): List<PersonMovie> {
        return list.filter {
            it.enProfession != "actor" &&  it.enProfession != "voice_actor"
        }
    }
}