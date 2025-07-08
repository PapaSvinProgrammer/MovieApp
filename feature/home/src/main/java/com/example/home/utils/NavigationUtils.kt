package com.example.home.utils

import androidx.navigation.NavController
import com.example.common.Constants
import com.example.model.image.CollectionMovie
import com.example.navigationroute.HomeDetailListRoute
import com.example.navigationroute.MovieListRoute

object NavigationUtils {
    const val DRAMA_GENRE = "драма"
    const val BOEVIK_GENRE = "боевик"
    const val LIST250 = "top250"
    const val LIST_SCIFI = "top_100_scifi_by_total_scifi_online"
    const val LIST501 = "best_501"
    const val HBO = "HBO"
    const val NETFLIX = "Netflix"

    fun NavController.navigateToMovieFromCollection(collection: CollectionMovie) {
        val query = listOf(
            Constants.LISTS_FIELD to collection.slug.toString(),
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        navigate(
            MovieListRoute(
                title = collection.name ?: "",
                queryParameters = query
            )
        ) {
            launchSingleTop = true
        }
    }

    fun NavController.navigateToHomeDetailByGenre(title: String, slug: String) {
        val query = arrayListOf(
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.GENRES_NAME_FIELD to slug
        )

        navigate(
            HomeDetailListRoute(
                title = title,
                queryParameters = query
            )
        ) { launchSingleTop = true }
    }

    fun NavController.navigateToHomeDetailByLists(title: String, slug: String) {
        val queryParameters = listOf(
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.LISTS_FIELD to slug
        )

        navigate(
            HomeDetailListRoute(
                title = title,
                queryParameters = queryParameters
            )
        ) { launchSingleTop = true }
    }

    fun NavController.navigateToHomeDetailByNetwork(title: String, slug: String) {
        val queryParameters = listOf(
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.NETWORK_ITEMS_NAME to slug
        )

        navigate(
            HomeDetailListRoute(
                title = title,
                queryParameters = queryParameters
            )
        ) { launchSingleTop = true }
    }
}