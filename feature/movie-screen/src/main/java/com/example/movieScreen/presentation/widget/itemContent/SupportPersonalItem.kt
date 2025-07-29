package com.example.movieScreen.presentation.widget.itemContent

import androidx.compose.foundation.lazy.LazyListScope
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

internal fun LazyListScope.supportPersonalItem(
    supportPersonal: List<PersonMovie>,
    navController: NavController
) {
    if (supportPersonal.isEmpty()) return

    item {
        TitleRow(title = stringResource(R.string.support_personal)) {

        }

        DefaultLazyRow(
            list = supportPersonal.take(10),
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
    }
}