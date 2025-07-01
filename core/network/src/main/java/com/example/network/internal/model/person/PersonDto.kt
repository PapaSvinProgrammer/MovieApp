package com.example.network.internal.model.person

import com.example.network.internal.model.movie.FactDto
import com.example.network.internal.model.movie.ShortMovieDto
import kotlinx.serialization.Serializable

@Serializable
internal data class PersonDto(
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
    val spouseDto: List<SpouseDto> = listOf(),
    val birthPlaceDto: List<PlaceDto> = listOf(),
    val deathPlaceDto: List<PlaceDto> = listOf(),
    val professionDto: List<ProfessionDto> = listOf(),
    val factDto: List<FactDto> = listOf(),
    val movies: List<ShortMovieDto> = listOf()
)