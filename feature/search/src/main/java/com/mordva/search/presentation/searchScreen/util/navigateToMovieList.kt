package com.mordva.search.presentation.searchScreen.util

import androidx.navigation.NavController
import com.mordva.model.image.CollectionMovie
import com.mordva.navigation.MovieListGraph
import com.mordva.util.Constants

internal fun navigateToMovieList(
    navController: NavController,
    collectionMovie: CollectionMovie
) {
    val query = arrayListOf(
        Constants.LISTS_FIELD to collectionMovie.slug.toString(),
        Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
        Constants.SORT_TYPE to Constants.SORT_DESC
    )

    navController.navigate(
        MovieListGraph.MovieListRoute(
            title = collectionMovie.name ?: "",
            queryParameters = query
        )
    ) { launchSingleTop = true }
}

internal val collectionCategoryList = listOf(
    "Онлайн-кинотеатр",
    "Премии",
    "Сборы",
    "Фильмы",
    "Сериалы"
)