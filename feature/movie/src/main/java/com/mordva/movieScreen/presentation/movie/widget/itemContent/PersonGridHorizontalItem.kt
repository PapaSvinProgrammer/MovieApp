package com.mordva.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movieapp.ui.R
import com.mordva.model.person.PersonMovie
import com.mordva.movieScreen.presentation.movie.widget.component.PersonGridHorizontalList
import com.mordva.navigation.PersonGraph
import com.mordva.ui.widget.component.TitleRow

internal fun LazyListScope.personGridHorizontalItem(
    actors: List<PersonMovie>,
    navController: NavController,
    onClick: () -> Unit
) {
    if (actors.isEmpty()) return

    item {
        TitleRow(
            title = stringResource(R.string.actors),
            onClick = onClick
        )

        PersonGridHorizontalList(
            list = actors.take(9),
            onClick = {
                navController.navigate(PersonGraph.PersonRoute(it.id)) {
                    launchSingleTop = true
                }
            }
        )
    }
}
