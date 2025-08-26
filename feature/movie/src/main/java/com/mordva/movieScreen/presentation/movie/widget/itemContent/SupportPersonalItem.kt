package com.mordva.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mordva.model.person.PersonMovie
import com.mordva.movieScreen.presentation.movie.widget.listItem.SupportPersonCard
import com.example.movieapp.ui.R
import com.mordva.movieScreen.presentation.navigation.GroupPersonRoute
import com.mordva.movieScreen.utils.toScreenObject
import com.mordva.navigation.PersonGraph
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard

internal fun LazyListScope.supportPersonalItem(
    supportPersonal: List<PersonMovie>,
    navController: NavController
) {
    if (supportPersonal.isEmpty()) return

    item {
        TitleRow(title = stringResource(R.string.support_personal)) {
            navController.navigate(
                GroupPersonRoute(supportPersonal.toScreenObject())
            ) { launchSingleTop = true }
        }

        DefaultLazyRow(
            list = supportPersonal.take(10),
            key = { it.id },
            lastItemCard = {
                LastItemCard(
                    width = 180.dp,
                    height = 100.dp
                )
            }
        ) {
            SupportPersonCard(
                person = it,
                onClick = {
                    navController.navigate(PersonGraph.PersonRoute(it.id)) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}