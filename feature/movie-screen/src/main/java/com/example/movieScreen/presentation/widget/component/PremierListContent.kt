package com.example.movieScreen.presentation.widget.component

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.model.totalValue.Premiere
import com.example.movieScreen.presentation.widget.listItem.PremiereCard
import com.example.movieapp.ui.R
import com.example.utils.convert.FormatDate

@Composable
internal fun PremierListContent(premiere: Premiere) {
    val state = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(state, SnapPosition.Start)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 15.dp),
        state = state,
        flingBehavior = flingBehavior
    ) {
        item {
            premiere.russia?.let {
                PremiereCard(
                    title = FormatDate.formatDate(it),
                    description = stringResource(R.string.release_russia)
                )

                Spacer(modifier = Modifier.width(10.dp))
            }
        }

        item {
            premiere.world?.let {
                PremiereCard(
                    title = FormatDate.formatDate(it),
                    description = stringResource(R.string.release_world)
                )

                Spacer(modifier = Modifier.width(10.dp))
            }
        }

        item {
            premiere.bluray?.let {
                PremiereCard(
                    title = FormatDate.formatDate(it),
                    description = stringResource(R.string.release_blu_ray)
                )

                Spacer(modifier = Modifier.width(10.dp))
            }
        }

        item {
            premiere.digital?.let {
                PremiereCard(
                    title = FormatDate.formatDate(it),
                    description = stringResource(R.string.release_digital)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
        }

        item {
            premiere.dvd?.let {
                PremiereCard(
                    title = FormatDate.formatDate(it),
                    description = stringResource(R.string.release_dvd)
                )
            }
        }
    }
}