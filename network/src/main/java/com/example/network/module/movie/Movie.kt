package com.example.network.module.movie

import com.example.network.module.person.Person
import com.example.network.module.category.Country
import com.example.network.module.category.Genre
import com.example.network.module.category.ProductionCompany
import com.example.network.module.image.Poster
import com.example.network.module.totalValue.Audience
import com.example.network.module.totalValue.Budget
import com.example.network.module.totalValue.Premiere
import com.example.network.module.totalValue.Rating
import com.example.network.module.totalValue.ReleaseYears
import com.example.network.module.totalValue.Votes
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val type: String?,
    val name: String?,
    val rating: Rating?,
    val description: String?,
    val votes: Votes?,
    val distributors: Distributors?,
    val premiere: Premiere?,
    val slogan: String?,
    val year: Int?,
    val budget: Budget?,
    val poster: Poster?,
    val facts: List<Fact> = listOf(),
    val genres: List<Genre> = listOf(),
    val countries: List<Country> = listOf(),
    val persons: List<Person> = listOf(),
    val productionCompanies: List<ProductionCompany> = listOf(),
    val alternativeName: String?,
    val backdrop: Poster?,
    val movieLength: Int?,
    val status: String?,
    val ageRating: Int?,
    val ratingMpaa: String?,
    val updatedAt: String?,
    val shortDescription: String?,
    val similarMovies: List<Movie> = listOf(),
    val sequelsAndPrequels: List<Movie> = listOf(),
    val logo: Poster?,
    val top10: Int?,
    val top250: Int?,
    val audience: List<Audience> = listOf(),
    val isSeries: Boolean?,
    val seriesLength: Int?,
    val totalSeriesLength: Int?,
    val releaseYears: List<ReleaseYears> = listOf()
)