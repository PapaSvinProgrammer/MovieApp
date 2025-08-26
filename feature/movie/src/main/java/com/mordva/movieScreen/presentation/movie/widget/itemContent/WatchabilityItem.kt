package com.mordva.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mordva.model.movie.Movie
import com.mordva.movieScreen.presentation.movie.widget.component.WatchabilityDescription
import com.mordva.movieScreen.presentation.navigation.WatchabilityListRoute
import com.mordva.movieScreen.utils.toScreenObject

internal fun LazyListScope.watchabilityItem(
    movie: Movie,
    navController: NavController
) {
    if (movie.watchability.items.isEmpty()) return

    item {
        WatchabilityDescription(
            modifier = Modifier.clickable {
                navController.navigate(
                    WatchabilityListRoute(
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
