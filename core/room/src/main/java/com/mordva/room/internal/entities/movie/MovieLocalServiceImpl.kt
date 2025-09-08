package com.mordva.room.internal.entities.movie

import com.mordva.model.movie.Movie
import com.mordva.room.external.MovieLocalService
import com.mordva.room.internal.utils.toCountryEntities
import com.mordva.room.internal.utils.toEntities
import com.mordva.room.internal.utils.toEntity
import com.mordva.room.internal.utils.toEpisodeEntities
import com.mordva.room.internal.utils.toGenreEntities
import com.mordva.room.internal.utils.toMovie
import com.mordva.room.internal.utils.toMovieEntity
import com.mordva.room.internal.utils.toSeasonEntities
import javax.inject.Inject

internal class MovieLocalServiceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieLocalService {
    override suspend fun insert(movie: Movie) {
        val movieEntity = movie.toMovieEntity()
        movieDao.insertMovie(movieEntity)

        movieDao.insertRatings(movie.rating.toEntity(movie.id))
        movieDao.insertVotes(movie.votes.toEntity(movie.id))
        movieDao.insertDistributors(movie.distributors.toEntity(movie.id))
        movieDao.insertPremieres(movie.premiere.toEntity(movie.id))
        movieDao.insertBudgets(movie.budget.toEntity(movie.id))

        movieDao.insertPosters(movie.poster.toEntity(movie.id, "poster"))
        movieDao.insertPosters(movie.backdrop.toEntity(movie.id, "backdrop"))
        movieDao.insertPosters(movie.logo.toEntity(movie.id, "logo"))

        movieDao.insertFacts(movie.facts.toEntities(movie.id))
        movieDao.insertGenres(movie.genres.toGenreEntities(movie.id))
        movieDao.insertCountries(movie.countries.toCountryEntities(movie.id))
        movieDao.insertPersons(movie.persons.toEntities(movie.id))
        movieDao.insertWatchabilityItems(movie.watchability.items.toEntities(movie.id))
        movieDao.insertAudience(movie.audience.toEntities(movie.id))
        movieDao.insertReleaseYears(movie.releaseYears.toEntities(movie.id))

        val seasons = movie.seasonsInfo?.toSeasonEntities() ?: emptyList()
        movieDao.insertSeasons(seasons)

        val episodes = movie.seasonsInfo?.toEpisodeEntities(seasons) ?: emptyList()
        movieDao.insertEpisodes(episodes)
    }

    override suspend fun getMovie(movieId: Int): Movie? {
        val movieWithDetails = movieDao.getMovieWithDetails(movieId) ?: return null
        return movieWithDetails.toMovie()
    }
}
