package com.mordva.movieScreen.presentation.movie

import androidx.lifecycle.ViewModel
import com.mordva.domain.repository.FavoritePackageRepository
import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.domain.repository.WillWatchPackageRepository
import com.mordva.domain.usecase.collection.GetCollectionBySlug
import com.mordva.domain.usecase.comment.GetCommentByDate
import com.mordva.domain.usecase.comment.model.CommentParams
import com.mordva.domain.usecase.movie.GetMovieById
import com.mordva.domain.usecase.movie.GetMovieImages
import com.mordva.domain.usecase.movie.model.MovieParams
import com.mordva.model.PackageType
import com.mordva.model.movie.Movie
import com.mordva.model.person.PersonMovie
import com.mordva.movieScreen.domain.FilterCollection
import com.mordva.movieScreen.domain.FilterPersonsLikeActors
import com.mordva.movieScreen.domain.FilterPersonsLikeSupport
import com.mordva.movieScreen.domain.FilterPersonsLikeVoiceActors
import com.mordva.movieScreen.domain.HandleFavoritePackageAction
import com.mordva.movieScreen.domain.HandleRatedMovieAction
import com.mordva.movieScreen.domain.HandleWillWatchAction
import com.mordva.movieScreen.domain.model.PackageItemParams
import com.mordva.movieScreen.domain.model.RatedMovieActionParams
import com.mordva.movieScreen.domain.model.WillWatchParams
import com.mordva.movieScreen.presentation.movie.widget.UIState
import com.mordva.movieScreen.presentation.movie.widget.scoreBottomSheet.RatedMovieState
import com.mordva.movieScreen.presentation.movie.widget.scoreBottomSheet.ScoreSheetAction
import com.mordva.movieScreen.utils.body
import com.mordva.room.external.MovieLocalService
import com.mordva.ui.uiState.MovieUIState
import com.mordva.ui.widget.packageBottomSheet.PackageItemAction
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import com.mordva.util.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class MovieViewModel @Inject constructor(
    private val getMovieById: GetMovieById,
    private val getMovieImages: GetMovieImages,
    private val getCollectionBySlug: GetCollectionBySlug,
    private val getCommentByDate: GetCommentByDate,
    private val filterCollection: FilterCollection,
    private val filterPersonsLikeVoiceActors: FilterPersonsLikeVoiceActors,
    private val filterPersonsLikeActors: FilterPersonsLikeActors,
    private val filterPersonsLikeSupport: FilterPersonsLikeSupport,
    private val ratedMovieRepository: RatedMovieRepository,
    private val willWatchPackageRepository: WillWatchPackageRepository,
    private val handleWillWatchAction: HandleWillWatchAction,
    private val handleRatedMovieAction: HandleRatedMovieAction,
    private val handleFavoritePackageAction: HandleFavoritePackageAction,
    private val favoritePackageRepository: FavoritePackageRepository,

    private val movieLocalService: MovieLocalService
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    fun save(movie: Movie) = launchWithoutOld {
        movieLocalService.insert(movie)
    }

    fun updatePackageVisible(visible: Boolean) {
        _uiState.update {
            it.copy(packageSheetVisible = visible)
        }
    }

    fun updateMoreSheetVisible(visible: Boolean) {
        _uiState.update {
            it.copy(moreSheetVisible = visible)
        }
    }

    fun updateScoreSheetVisible(visible: Boolean) {
        _uiState.update {
            it.copy(scoreSheetVisible = visible)
        }
    }

    fun updateCollapsedState(state: Boolean) {
        _uiState.update {
            it.copy(isCollapsed = state)
        }
    }

    fun updateSelectedFact(text: String) {
        _uiState.update {
            it.copy(selectedFact = text)
        }
    }

    fun updateCurrentRatingMovie(rating: Int) {
        _uiState.update { it.copy(currentMovieRating = rating) }
    }

    fun handleScoreSheetHandle(action: ScoreSheetAction) = launchWithoutOld(RATED_MOVIE_JOB) {
        val params = RatedMovieActionParams(
            scoreSheetAction = action,
            currentScore = uiState.value.currentMovieRating,
            movie = uiState.value.movieState.body(),
        )

        handleRatedMovieAction.execute(params)
    }

    fun isRatedMovie() = launchWithoutOld(IS_RATED_MOVIE_JOB) {
        val movieId = uiState.value.movieState.body().id

        ratedMovieRepository.isStock(movieId).collect { ratedMovie ->
            _uiState.update {
                it.copy(isRatedMovieState = ratedMovie)
            }
        }
    }

    fun getComments(movieId: Int) = launchWithoutOld(GET_COMMENTS_JOB) {
        val params = CommentParams(
            movieId = movieId,
            sort = -1
        )

        getCommentByDate.execute(params).onSuccess { comments ->
            _uiState.update {
                it.copy(comments = comments)
            }
        }
    }

    fun getMovie(id: Int) = launchWithoutOld(GET_MOVIE_JOB) {
        val res = getMovieById.execute(id)

        res.onSuccess { movie ->
            val state = MovieUIState.Success(listOf(movie))

            _uiState.update {
                it.copy(movieState = state)
            }

            filterActors(movie.persons)
        }
    }

    fun getImages(movieId: Int) = launchWithoutOld(GET_IMAGES_JOB) {
        val params = MovieParams(
            movieId = movieId
        )

        val res = getMovieImages.execute(params)

        res.onSuccess { images ->
            _uiState.update { it.copy(images = images) }
        }
    }

    fun getCollections(list: List<String>) = launchWithoutOld(GET_COLLECTIONS) {
        if (uiState.value.collections.isNotEmpty()) {
            return@launchWithoutOld
        }

        val temp = multiRequest(list) {
            getCollectionBySlug.execute(it)
        }

        if (temp.isNotEmpty()) {
            val res = filterCollection.execute(temp)

            _uiState.update {
                it.copy(collections = res)
            }
        }
    }

    fun isWillWatchPackage() = launchWithoutOld(IS_WILL_WATCH_JOB) {
        val movieId = uiState.value.movieState.body().id

        willWatchPackageRepository.isStock(movieId).collect { result ->
            val set = uiState.value.selectedPackage.toMutableSet()

            _uiState.update {
                it.copy(isWillWatch = result != null)
            }

            if (result != null) {
                set.add(PackageType.WILL_WATCH)
            } else {
                set.remove(PackageType.WILL_WATCH)
            }

            _uiState.update { it.copy(selectedPackage = set) }
        }
    }

    fun handleWillWatchAction() = launchWithoutOld(WILL_WATCH_JOB) {
        val params = WillWatchParams(
            isChecked = uiState.value.isWillWatch,
            movieId = uiState.value.movieState.body().id
        )

        handleWillWatchAction.execute(params)
    }

    fun getRatedMovies(rating: Int) = launchWithoutOld(RATED_MOVIE_JOB) {
        _uiState.update { it.copy(ratedMoviesState = RatedMovieState.Loading) }

        ratedMovieRepository.getAllByRating(rating).collect { movies ->
            _uiState.update {
                it.copy(ratedMoviesState = RatedMovieState.Success(movies))
            }
        }
    }

    fun handlePackageAction(action: PackageItemAction) = launchWithoutOld(FAVORITE_PACKAGE_JOB) {
        val params = PackageItemParams(
            action = action,
            movieId = uiState.value.movieState.body().id
        )

        handleFavoritePackageAction.execute(params)
    }

    fun getInfoForPackages() {
        val movieId = uiState.value.movieState.body().id

        isFavoritePackage(movieId)
        getCountFavoritePackage()
        getCountWillWatchPackage()
    }

    private fun getCountFavoritePackage() = launchWithoutOld(COUNT_FAVORITE_JOB) {
        favoritePackageRepository.count().collect { count ->
            val map = uiState.value.packageSize.toMutableMap()
            map[PackageType.FAVORITE] = count
            _uiState.update { it.copy(packageSize = map) }
        }
    }

    private fun getCountWillWatchPackage() = launchWithoutOld(COUNT_WILL_WATCH_JOB) {
        willWatchPackageRepository.count().collect { count ->
            val map = uiState.value.packageSize.toMutableMap()
            map[PackageType.WILL_WATCH] = count
            _uiState.update { it.copy(packageSize = map) }
        }
    }

    private fun isFavoritePackage(movieId: Int) = launchWithoutOld(IS_FAVORITE_PACKAGE_JOB) {
        favoritePackageRepository.isStock(movieId).collect { moviePackage ->
            val set = uiState.value.selectedPackage.toMutableSet()

            if (moviePackage != null) {
                set.add(PackageType.FAVORITE)
            } else {
                set.remove(PackageType.FAVORITE)
            }

            _uiState.update { it.copy(selectedPackage = set) }
        }
    }

    private fun filterActors(list: List<PersonMovie>) = launchWithoutOld(FILTER_ACTORS_JOB) {
        _uiState.update {
            it.copy(
                actors = filterPersonsLikeActors.execute(list),
                voiceActors = filterPersonsLikeVoiceActors.execute(list),
                supportPersonal = filterPersonsLikeSupport.execute(list)
            )
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val COUNT_WILL_WATCH_JOB = "count_will_watch_job"
        const val COUNT_FAVORITE_JOB = "count_favorite_package"
        const val IS_FAVORITE_PACKAGE_JOB = "is_favorite_package"
        const val FAVORITE_PACKAGE_JOB = "action_favorite_package"
        const val WILL_WATCH_JOB = "action_will_watch_job"
        const val IS_WILL_WATCH_JOB = "is_will_watch_package"
        const val RATED_MOVIE_JOB = "action_for_rated_movie"
        const val IS_RATED_MOVIE_JOB = "is_rated_movie"
        const val GET_COMMENTS_JOB = "get_comments"
        const val GET_MOVIE_JOB = "get_movie"
        const val GET_IMAGES_JOB = "get_images"
        const val GET_COLLECTIONS = "get_collections"
        const val FILTER_ACTORS_JOB = "filter_actors"
    }
}
