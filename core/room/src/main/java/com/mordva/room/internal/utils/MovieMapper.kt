package com.mordva.room.internal.utils

import com.mordva.model.category.ItemName
import com.mordva.model.category.WatchabilityItem
import com.mordva.model.image.Poster
import com.mordva.model.movie.Distributors
import com.mordva.model.movie.Fact
import com.mordva.model.movie.Movie
import com.mordva.model.movie.Watchability
import com.mordva.model.person.PersonMovie
import com.mordva.model.season.Season
import com.mordva.model.totalValue.Audience
import com.mordva.model.totalValue.Budget
import com.mordva.model.totalValue.Premiere
import com.mordva.model.totalValue.Rating
import com.mordva.model.totalValue.ReleaseYears
import com.mordva.model.totalValue.Votes
import com.mordva.room.internal.entities.movie.MovieDetails
import com.mordva.room.internal.entities.movie.entity.AudienceEntity
import com.mordva.room.internal.entities.movie.entity.BudgetEntity
import com.mordva.room.internal.entities.movie.entity.CountryEntity
import com.mordva.room.internal.entities.movie.entity.DistributorEntity
import com.mordva.room.internal.entities.movie.entity.EpisodeEntity
import com.mordva.room.internal.entities.movie.entity.FactEntity
import com.mordva.room.internal.entities.movie.entity.GenreEntity
import com.mordva.room.internal.entities.movie.entity.MovieEntity
import com.mordva.room.internal.entities.movie.entity.PersonMovieEntity
import com.mordva.room.internal.entities.movie.entity.PosterEntity
import com.mordva.room.internal.entities.movie.entity.PremiereEntity
import com.mordva.room.internal.entities.movie.entity.RatingEntity
import com.mordva.room.internal.entities.movie.entity.ReleaseYearsEntity
import com.mordva.room.internal.entities.movie.entity.SeasonEntity
import com.mordva.room.internal.entities.movie.entity.VotesEntity
import com.mordva.room.internal.entities.movie.entity.WatchabilityItemEntity

internal fun Movie.toMovieEntity() = MovieEntity(
    id = id,
    type = type,
    name = name,
    description = description,
    slogan = slogan,
    year = year,
    alternativeName = alternativeName,
    movieLength = movieLength,
    status = status,
    ageRating = ageRating,
    ratingMpaa = ratingMpaa,
    updatedAt = updatedAt,
    shortDescription = shortDescription,
    top10 = top10,
    top250 = top250,
    isSeries = isSeries,
    seriesLength = seriesLength,
    totalSeriesLength = totalSeriesLength
)

internal fun Rating?.toEntity(movieId: Int) = this?.let {
    listOf(
        RatingEntity(
            movieId = movieId,
            kp = it.kp,
            imdb = it.imdb,
            filmCritics = it.filmCritics,
            russianFilmCritics = it.russianFilmCritics
        )
    )
} ?: emptyList()

internal fun Votes?.toEntity(movieId: Int) = this?.let {
    listOf(
        VotesEntity(
            movieId = movieId,
            kp = it.kp,
            imdb = it.imdb,
            filmCritics = it.filmCritics,
            russianFilmCritics = it.russianFilmCritics,
            await = it.await
        )
    )
} ?: emptyList()

internal fun Distributors?.toEntity(movieId: Int) = this?.let {
    listOf(
        DistributorEntity(
            movieId = movieId,
            distributor = it.distributor,
            distributorRelease = it.distributorRelease
        )
    )
} ?: emptyList()

internal fun Premiere?.toEntity(movieId: Int) = this?.let {
    listOf(
        PremiereEntity(
            movieId = movieId,
            bluray = it.bluray,
            digital = it.digital,
            dvd = it.dvd,
            russia = it.russia,
            world = it.world
        )
    )
} ?: emptyList()

internal fun Budget?.toEntity(movieId: Int) = this?.let {
    listOf(
        BudgetEntity(
            movieId = movieId,
            value = it.value,
            currency = it.currency
        )
    )
} ?: emptyList()

internal fun Poster?.toEntity(movieId: Int, type: String) = this?.let {
    listOf(
        PosterEntity(
            movieId = movieId,
            type = type,
            url = it.url,
            previewUrl = it.previewUrl
        )
    )
} ?: emptyList()

@JvmName("listFactToEntities")
internal fun List<Fact>.toEntities(movieId: Int) = map {
    FactEntity(
        movieId = movieId,
        value = it.value,
        type = it.type,
        spoiler = it.spoiler
    )
}

@JvmName("listItemNameToGenreEntities")
internal fun List<ItemName>.toGenreEntities(movieId: Int) = map {
    GenreEntity(movieId = movieId, name = it.name, slug = it.slug)
}

@JvmName("listItemNameToCountryEntities")
internal fun List<ItemName>.toCountryEntities(movieId: Int) = map {
    CountryEntity(movieId = movieId, name = it.name, slug = it.slug)
}

@JvmName("listPersonMovieToEntities")
internal fun List<PersonMovie>.toEntities(movieId: Int) = map {
    PersonMovieEntity(
        id = it.id,
        movieId = movieId,
        name = it.name,
        enName = it.enName,
        photo = it.photo,
        description = it.description,
        profession = it.profession,
        enProfession = it.enProfession
    )
}

@JvmName("listWatchabilityItemToEntities")
internal fun List<WatchabilityItem>.toEntities(movieId: Int) = map {
    WatchabilityItemEntity(
        movieId = movieId,
        name = it.name,
        url = it.url
    )
}

@JvmName("listAudienceToEntities")
internal fun List<Audience>.toEntities(movieId: Int) = map {
    AudienceEntity(
        movieId = movieId,
        count = it.count,
        country = it.country
    )
}

@JvmName("listReleaseYears")
internal fun List<ReleaseYears>.toEntities(movieId: Int) = map {
    ReleaseYearsEntity(
        movieId = movieId,
        start = it.start,
        end = it.end
    )
}

@JvmName("listSeasonToSeasonEntities")
internal fun List<Season>.toSeasonEntities() = map {
    SeasonEntity(
        movieId = it.movieId,
        number = it.number,
        name = it.name,
        enName = it.enName,
        episodesCount = it.episodesCount,
        airDate = it.airDate
    )
}

internal fun List<Season>.toEpisodeEntities(seasons: List<SeasonEntity>): List<EpisodeEntity> {
    val result = mutableListOf<EpisodeEntity>()
    for ((index, season) in this.withIndex()) {
        val seasonId = seasons.getOrNull(index)?.seasonId ?: continue
        season.episodes.forEach { ep ->
            result.add(
                EpisodeEntity(
                    seasonOwnerId = seasonId,
                    number = ep.number ?: 0,
                    name = ep.name,
                    enName = ep.enName,
                    airDate = ep.airDate
                )
            )
        }
    }
    return result
}

internal fun MovieDetails.toMovie(): Movie {
    return Movie(
        id = movie.id,
        type = movie.type,
        name = movie.name,
        description = movie.description,
        slogan = movie.slogan,
        year = movie.year,
        alternativeName = movie.alternativeName,
        movieLength = movie.movieLength,
        status = movie.status,
        ageRating = movie.ageRating,
        ratingMpaa = movie.ratingMpaa,
        updatedAt = movie.updatedAt,
        shortDescription = movie.shortDescription,
        top10 = movie.top10,
        top250 = movie.top250,
        isSeries = movie.isSeries,
        seriesLength = movie.seriesLength,
        totalSeriesLength = movie.totalSeriesLength,
        rating = ratings.firstOrNull()?.let {
            Rating(it.kp, it.imdb, it.filmCritics, it.russianFilmCritics)
        },
        votes = votes.firstOrNull()?.let {
            Votes(it.kp, it.imdb, it.filmCritics, it.russianFilmCritics, it.await)
        },
        distributors = distributors.firstOrNull()?.let {
            Distributors(it.distributor, it.distributorRelease)
        },
        premiere = premieres.firstOrNull()?.let {
            Premiere(it.bluray, it.digital, it.dvd, it.russia, it.world)
        },
        budget = budgets.firstOrNull()?.let {
            Budget(it.value, it.currency)
        },
        poster = posters.find { it.type == "poster" }?.let {
            Poster(it.url, it.previewUrl)
        },
        backdrop = posters.find { it.type == "backdrop" }?.let {
            Poster(it.url, it.previewUrl)
        },
        logo = posters.find { it.type == "logo" }?.let {
            Poster(it.url, it.previewUrl)
        },
        facts = facts.map { Fact(it.value, it.type, it.spoiler) },
        genres = genres.map { ItemName(it.name, it.slug) },
        countries = countries.map { ItemName(it.name, it.slug) },
        persons = persons.map {
            PersonMovie(
                id = it.id,
                name = it.name,
                enName = it.enName,
                photo = it.photo,
                description = it.description,
                profession = it.profession,
                enProfession = it.enProfession
            )
        },
        watchability = Watchability(
            watchability.map { WatchabilityItem(it.name, null, it.url) }
        ),
        audience = audience.map { Audience(it.count, it.country) },
        releaseYears = releaseYears.map { ReleaseYears(it.start, it.end) },
        seasonsInfo = seasons.map {
            Season(
                movieId = it.movieId,
                number = it.number,
                name = it.name,
                enName = it.enName,
                episodesCount = it.episodesCount,
                airDate = it.airDate,
                episodes = emptyList()
            )
        }
    )
}
