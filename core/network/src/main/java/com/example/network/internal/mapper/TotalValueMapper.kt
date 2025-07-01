package com.example.network.internal.mapper

import com.example.model.totalValue.Audience
import com.example.network.internal.model.totalValue.AudienceDto
import com.example.model.totalValue.Budget
import com.example.network.internal.model.totalValue.BudgetDto
import com.example.model.totalValue.Premiere
import com.example.network.internal.model.totalValue.PremiereDto
import com.example.model.totalValue.Rating
import com.example.model.totalValue.ReleaseYears
import com.example.model.totalValue.Votes
import com.example.network.internal.model.totalValue.RatingDto
import com.example.network.internal.model.totalValue.ReleaseYearsDto
import com.example.network.internal.model.totalValue.VotesDto

internal fun AudienceDto.toDomain(): Audience = Audience(
    count = this.count,
    country = this.country
)

internal fun BudgetDto.toDomain(): Budget = Budget(
    value = this.value,
    currency = this.currency
)

internal fun PremiereDto.toDomain(): Premiere = Premiere(
    world = this.world,
    dvd = this.dvd
)

internal fun RatingDto.toDomain(): Rating = Rating(
    kp = this.kp,
    imdb = this.imdb,
    filmCritics = this.filmCritics,
    russianFilmCritics = this.russianFilmCritics
)

internal fun ReleaseYearsDto.toDomain(): ReleaseYears = ReleaseYears(
    start = this.start,
    end = this.end
)

internal fun VotesDto.toDomain(): Votes = Votes(
    kp = this.kp,
    imdb = this.imdb,
    filmCritics = this.filmCritics,
    russianFilmCritics = this.russianFilmCritics,
    await = this.await
)
