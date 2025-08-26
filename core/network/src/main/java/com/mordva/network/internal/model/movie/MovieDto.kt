package com.mordva.network.internal.model.movie

import com.mordva.network.internal.model.image.PosterDto
import com.mordva.network.internal.model.person.PersonMovieDto
import com.mordva.network.internal.model.season.SeasonDto
import com.mordva.network.internal.model.totalValue.AudienceDto
import com.mordva.network.internal.model.totalValue.BudgetDto
import com.mordva.network.internal.model.totalValue.PremiereDto
import com.mordva.network.internal.model.totalValue.RatingDto
import com.mordva.network.internal.model.totalValue.ReleaseYearsDto
import com.mordva.network.internal.model.totalValue.VotesDto
import com.mordva.network.internal.model.category.ItemNameDto
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDto(
    val id: Int,
    val type: String? = null,
    val name: String? = null,
    val rating: RatingDto? = null,
    val description: String? = null,
    val votes: VotesDto? = null,
    val distributors: DistributorsDto? = null,
    val premiere: PremiereDto? = null,
    val slogan: String? = null,
    val year: Int? = null,
    val budget: BudgetDto? = null,
    val poster: PosterDto? = null,
    val facts: List<FactDto>? = null,
    val genres: List<ItemNameDto> = listOf(),
    val countries: List<ItemNameDto> = listOf(),
    val persons: List<PersonMovieDto> = listOf(),
    val watchability: WatchabilityDto = WatchabilityDto(),
    val alternativeName: String? = null,
    val backdrop: PosterDto? = null,
    val movieLength: Int? = null,
    val status: String? = null,
    val ageRating: Int? = null,
    val ratingMpaa: String? = null,
    val updatedAt: String? = null,
    val shortDescription: String? = null,
    val similarMovies: List<MovieDto> = listOf(),
    val sequelsAndPrequels: List<MovieDto> = listOf(),
    val logo: PosterDto? = null,
    val top10: Int? = null,
    val top250: Int? = null,
    val audience: List<AudienceDto> = listOf(),
    val isSeries: Boolean? = null,
    val seriesLength: Int? = null,
    val totalSeriesLength: Int? = null,
    val releaseYears: List<ReleaseYearsDto> = listOf(),
    val seasonsInfo: List<SeasonDto>? = null,
    val lists: List<String> = listOf()
)
