package com.example.network.module.person

import com.example.network.module.movie.Fact
import com.example.network.module.movie.ShortMovie
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: Int,
    val name: String?,
    val enName: String?,
    val photo: String?,
    val sex: String?,
    val growth: Int?,
    val birthday: String?,
    val death: String?,
    val age: Int?,
    val countAwards: Int?,
    val birthPlace: List<Place> = listOf(),
    val deathPlace: List<Place> = listOf(),
    val professions: List<Profession> = listOf(),
    val facts: List<Fact> = listOf(),
    val movies: List<ShortMovie> = listOf()
)