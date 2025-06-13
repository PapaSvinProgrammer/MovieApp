package com.example.network.module.person

import com.example.network.module.movie.Fact
import com.example.network.module.movie.ShortMovie
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: Int = 0,
    val name: String? = null,
    val enName: String? = null,
    val photo: String? = null,
    val sex: String? = null,
    val growth: Int? = null,
    val birthday: String? = null,
    val death: String? = null,
    val age: Int? = null,
    val countAwards: Int? = null,
    val spouses: List<Spouse> = listOf(),
    val birthPlace: List<Place> = listOf(),
    val deathPlace: List<Place> = listOf(),
    val professions: List<Profession> = listOf(),
    val facts: List<Fact> = listOf(),
    val movies: List<ShortMovie> = listOf()
)