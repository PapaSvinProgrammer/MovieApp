package com.mordva.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.ui.R
import com.mordva.model.person.PersonMovie
import com.mordva.movieScreen.presentation.movie.widget.listItem.SupportPersonCard
import com.mordva.movieScreen.presentation.navigation.GroupPersonRoute
import com.mordva.movieScreen.utils.toScreenObject
import com.mordva.navigation.PersonGraph
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard

internal fun LazyListScope.voiceActorsItem(
    voiceActors: List<PersonMovie>,
    navController: NavController
) {
    if (voiceActors.isEmpty()) return

    item {
        TitleRow(title = stringResource(R.string.voice_actors)) {
            navController.navigate(
                GroupPersonRoute(voiceActors.toScreenObject())
            ) { launchSingleTop = true }
        }

        DefaultLazyRow(
            list = voiceActors.take(10),
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

        Spacer(modifier = Modifier.height(30.dp))
    }
}