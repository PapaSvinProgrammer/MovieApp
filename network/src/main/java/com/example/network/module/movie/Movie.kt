package com.example.network.module.movie

import com.example.network.module.category.Country
import com.example.network.module.category.Genre
import com.example.network.module.image.Poster
import com.example.network.module.person.PersonMovie
import com.example.network.module.season.Season
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
    val type: String? = null,
    val name: String? = null,
    val rating: Rating? = null,
    val description: String? = null,
    val votes: Votes? = null,
    val distributors: Distributors? = null,
    val premiere: Premiere? = null,
    val slogan: String? = null,
    val year: Int? = null,
    val budget: Budget? = null,
    val poster: Poster? = null,
    val facts: List<Fact> = listOf(),
    val genres: List<Genre> = listOf(),
    val countries: List<Country> = listOf(),
    val persons: List<PersonMovie> = listOf(),
    val watchability: Watchability = Watchability(),
    val alternativeName: String? = null,
    val backdrop: Poster? = null,
    val movieLength: Int? = null,
    val status: String? = null,
    val ageRating: Int? = null,
    val ratingMpaa: String? = null,
    val updatedAt: String? = null,
    val shortDescription: String? = null,
    val similarMovies: List<Movie> = listOf(),
    val sequelsAndPrequels: List<Movie> = listOf(),
    val logo: Poster? = null,
    val top10: Int? = null,
    val top250: Int? = null,
    val audience: List<Audience> = listOf(),
    val isSeries: Boolean? = null,
    val seriesLength: Int? = null,
    val totalSeriesLength: Int? = null,
    val releaseYears: List<ReleaseYears> = listOf(),
    val seasonsInfo: List<Season>? = null,
    val lists: List<String> = listOf()
)
