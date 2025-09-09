package com.mordva.movieScreen.domain

import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.movieScreen.domain.model.RatedMovieActionParams
import com.mordva.movieScreen.domain.model.RatedMovieParams
import com.mordva.movieScreen.presentation.movie.widget.scoreBottomSheet.ScoreSheetAction
import com.mordva.movieScreen.utils.toRatedMovie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class HandleRatedMovieAction @Inject constructor(
    private val ratedMovieRepository: RatedMovieRepository
) : UseCase<RatedMovieActionParams, Unit>(Dispatchers.IO) {
    override suspend fun run(params: RatedMovieActionParams) {
        when (params.scoreSheetAction) {
            ScoreSheetAction.Delete -> {
                ratedMovieRepository.delete(params.movie.id)
            }

            is ScoreSheetAction.Save -> {
                val params = RatedMovieParams(
                    movie = params.movie,
                    rating = params.scoreSheetAction.rating
                )

                val ratedMovie = params.movie.toRatedMovie(params.rating)
                ratedMovieRepository.add(ratedMovie)
            }

            ScoreSheetAction.Nothing -> {}
        }
    }
}