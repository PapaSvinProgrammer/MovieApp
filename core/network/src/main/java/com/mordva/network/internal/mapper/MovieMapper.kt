package com.mordva.network.internal.mapper

import com.mordva.model.movie.Comment
import com.mordva.network.internal.model.movie.CommentDto
import com.mordva.model.movie.Distributors
import com.mordva.model.movie.Fact
import com.mordva.network.internal.model.movie.FactDto
import com.mordva.model.movie.Movie
import com.mordva.model.movie.ShortMovie
import com.mordva.model.movie.Studio
import com.mordva.model.movie.Watchability
import com.mordva.network.internal.model.movie.MovieDto
import com.mordva.network.internal.model.movie.DistributorsDto
import com.mordva.network.internal.model.movie.ShortMovieDto
import com.mordva.network.internal.model.movie.StudioDto
import com.mordva.network.internal.model.movie.WatchabilityDto

internal fun CommentDto.toDomain(): Comment = Comment(
    id = this.id,
    movieId = this.movieId,
    title = this.title,
    type = this.type,
    review = this.review,
    date = this.date,
    author = this.author,
    userRating = this.userRating,
    authorId = this.authorId,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    reviewDislikes = this.reviewDislikes,
    reviewLikes = this.reviewLikes
)

internal fun DistributorsDto.toDomain(): Distributors = Distributors(
    distributor = this.distributor,
    distributorRelease = this.distributorRelease
)

internal fun FactDto.toDomain(): Fact = Fact(
    value = this.value,
    type = this.type,
    spoiler = this.spoiler
)

internal fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    type = type,
    name = name,
    rating = rating?.toDomain(),
    description = description,
    votes = votes?.toDomain(),
    distributors = distributors?.toDomain(),
    premiere = premiere?.toDomain(),
    slogan = slogan,
    year = year,
    budget = budget?.toDomain(),
    poster = poster?.toDomain(),
    facts = facts?.map { it.toDomain() } ?: listOf(),
    genres = genres.map { it.toDomain() },
    countries = countries.map { it.toDomain() },
    persons = persons.map { it.toDomain() },
    watchability = watchability.toDomain(),
    alternativeName = alternativeName,
    backdrop = backdrop?.toDomain(),
    movieLength = movieLength,
    status = status,
    ageRating = ageRating,
    ratingMpaa = ratingMpaa,
    updatedAt = updatedAt,
    shortDescription = shortDescription,
    similarMovies = similarMovies.map { it.toDomain() },
    sequelsAndPrequels = sequelsAndPrequels.map { it.toDomain() },
    logo = logo?.toDomain(),
    top10 = top10,
    top250 = top250,
    audience = audience.map { it.toDomain() },
    isSeries = isSeries,
    seriesLength = seriesLength,
    totalSeriesLength = totalSeriesLength,
    releaseYears = releaseYears.map { it.toDomain() },
    seasonsInfo = seasonsInfo?.map { it.toDomain() },
    lists = lists
)

internal fun ShortMovieDto.toDomain(): ShortMovie = ShortMovie(
    id = this.id,
    name = this.name,
    alternativeName = this.alternativeName,
    rating = this.rating,
    description = this.description,
    enProfession = this.enProfession
)

internal fun StudioDto.toDomain(): Studio = Studio(
    id = this.id,
    subType = this.subType,
    title = this.title,
    type = this.type,
    movies = movie.map { it.toDomain() }
)

internal fun WatchabilityDto.toDomain(): Watchability = Watchability(
    items = this.items?.map { it.toDomain() } ?: listOf()
)
