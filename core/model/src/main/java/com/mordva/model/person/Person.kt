package com.mordva.model.person

import com.mordva.model.movie.Fact
import com.mordva.model.movie.ShortMovie

data class Person(
    val id: Int = 0,
    val name: String?,
    val enName: String?,
    val photo: String?,
    val sex: String?,
    val growth: Int?,
    val birthday: String?,
    val death: String?,
    val age: Int?,
    val countAwards: Int?,
    val spouses: List<Spouse>,
    val birthPlace: List<Place>,
    val deathPlace: List<Place>,
    val professions: List<Profession>,
    val facts: List<Fact>,
    val movies: List<ShortMovie>
)