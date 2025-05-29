package com.example.network.module.person

import com.example.network.module.movie.Fact
import com.example.network.module.movie.Movie
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: Int?,
    val name: String?,
    val enName: String?,
    val photo: String?,
    val sex: String?,
    val growth: Int?,
    val birthday: String?,
    val age: Int?,
    val facts: List<Fact>?,
    val movies: List<Movie>?,
)
