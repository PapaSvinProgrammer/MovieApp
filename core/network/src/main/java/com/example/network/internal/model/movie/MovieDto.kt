package com.example.network.internal.model.movie

import com.example.network.internal.model.image.PosterDto
import com.example.network.internal.model.person.PersonMovieDto
import com.example.network.internal.model.season.SeasonDto
import com.example.network.internal.model.totalValue.AudienceDto
import com.example.network.internal.model.totalValue.BudgetDto
import com.example.network.internal.model.totalValue.PremiereDto
import com.example.network.internal.model.totalValue.RatingDto
import com.example.network.internal.model.totalValue.ReleaseYearsDto
import com.example.network.internal.model.totalValue.VotesDto
import com.example.network.internal.model.category.ItemNameDto
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDto(
    val id: Int,
    val type: String? = null,
    val name: String? = null,
    val ratingDto: RatingDto? = null,
    val description: String? = null,
    val votesDto: VotesDto? = null,
    val distributorsDto: DistributorsDto? = null,
    val premiereDto: PremiereDto? = null,
    val slogan: String? = null,
    val year: Int? = null,
    val budgetDto: BudgetDto? = null,
    val posterDto: PosterDto? = null,
    val factDtos: List<FactDto> = listOf(),
    val genreDtos: List<ItemNameDto> = listOf(),
    val countries: List<ItemNameDto> = listOf(),
    val persons: List<PersonMovieDto> = listOf(),
    val watchabilityDto: WatchabilityDto = WatchabilityDto(),
    val alternativeName: String? = null,
    val backdrop: PosterDto? = null,
    val movieLength: Int? = null,
    val status: String? = null,
    val ageRating: Int? = null,
    val ratingMpaa: String? = null,
    val updatedAt: String? = null,
    val shortDescription: String? = null,
    val similarMovieDtos: List<MovieDto> = listOf(),
    val sequelsAndPrequels: List<MovieDto> = listOf(),
    val logo: PosterDto? = null,
    val top10: Int? = null,
    val top250: Int? = null,
    val audienceDto: List<AudienceDto> = listOf(),
    val isSeries: Boolean? = null,
    val seriesLength: Int? = null,
    val totalSeriesLength: Int? = null,
    val releaseYearDtos: List<ReleaseYearsDto> = listOf(),
    val seasonsInfo: List<SeasonDto>? = null,
    val lists: List<String> = listOf()
)
