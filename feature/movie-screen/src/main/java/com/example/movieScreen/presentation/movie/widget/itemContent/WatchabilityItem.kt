package com.example.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.model.movie.Movie
import com.example.movieScreen.presentation.movie.widget.component.WatchabilityDescription
import com.example.navigationroute.MovieRoutes
import com.example.navigationroute.model.toScreenObject

internal fun LazyListScope.watchabilityItem(
    movie: Movie,
    navController: NavController
) {
    if (movie.watchability.items.isEmpty()) return

    item {
        WatchabilityDescription(
            modifier = Modifier.clickable {
                navController.navigate(
                    MovieRoutes.WatchabilityListRoute(
                        watchability = movie.watchability.toScreenObject()
                    )
                ) {
                    launchSingleTop = true
                }
            },
            count = movie.watchability.items.size
        )
    }
}
