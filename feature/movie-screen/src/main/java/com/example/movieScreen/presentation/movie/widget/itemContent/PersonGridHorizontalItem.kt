package com.example.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.model.person.PersonMovie
import com.example.movieScreen.presentation.movie.widget.component.PersonGridHorizontalList
import com.example.movieapp.ui.R
import com.example.navigationroute.PersonRoute
import com.example.ui.widget.component.TitleRow

internal fun LazyListScope.personGridHorizontalItem(
    actors: List<PersonMovie>,
    navController: NavController
) {
    if (actors.isEmpty()) return

    item {
        TitleRow(
            title = stringResource(R.string.actors),

        ) {}

        PersonGridHorizontalList(
            list = actors.take(9),
            onClick = {
                navController.navigate(PersonRoute(it.id)) {
                    launchSingleTop = true
                }
            }
        )
    }
}