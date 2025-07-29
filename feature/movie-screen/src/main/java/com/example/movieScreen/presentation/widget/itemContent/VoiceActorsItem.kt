package com.example.movieScreen.presentation.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.model.person.PersonMovie
import com.example.movieScreen.presentation.widget.listItem.SupportPersonCard
import com.example.movieapp.ui.R
import com.example.navigationroute.PersonRoute
import com.example.ui.widget.component.TitleRow
import com.example.ui.widget.lazyComponent.DefaultLazyRow
import com.example.ui.widget.listItems.LastItemCard

internal fun LazyListScope.voiceActorsItem(
    voiceActors: List<PersonMovie>,
    navController: NavController
) {
    if (voiceActors.isEmpty()) return

    item {
        TitleRow(title = stringResource(R.string.voice_actors)) {

        }

        DefaultLazyRow(
            list = voiceActors.take(10),
            key = { it.id },
            lastItemCard = {
                LastItemCard(
                    width = 180.dp,
                    height = 110.dp
                )
            }
        ) {
            SupportPersonCard(
                person = it,
                onClick = {
                    navController.navigate(PersonRoute(it.id)) {
                        launchSingleTop = true
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}