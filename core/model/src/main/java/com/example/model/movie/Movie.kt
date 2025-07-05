package com.example.model.movie

import com.example.model.category.ItemName
import com.example.model.image.Poster
import com.example.model.person.PersonMovie
import com.example.model.season.Season
import com.example.model.totalValue.Audience
import com.example.model.totalValue.Budget
import com.example.model.totalValue.Premiere
import com.example.model.totalValue.Rating
import com.example.model.totalValue.ReleaseYears
import com.example.model.totalValue.Votes

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
    val genres: List<ItemName> = listOf(),
    val countries: List<ItemName> = listOf(),
    val persons: List<PersonMovie> = listOf(),
    val watchability: Watchability = Watchability(listOf()),
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
