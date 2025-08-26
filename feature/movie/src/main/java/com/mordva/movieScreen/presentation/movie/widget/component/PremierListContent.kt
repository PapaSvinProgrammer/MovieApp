package com.mordva.movieScreen.presentation.movie.widget.component

import android.content.Context
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mordva.model.totalValue.Premiere
import com.mordva.movieScreen.presentation.movie.widget.listItem.PremiereCard
import com.example.movieapp.ui.R
import com.mordva.util.convert.FormatDate

@Composable
internal fun PremierListContent(premiere: Premiere) {
    val context = LocalContext.current
    val state = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(state, SnapPosition.Start)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 15.dp),
        state = state,
        flingBehavior = flingBehavior
    ) {
        items(premiere.toMap().toList()) { pair ->
            pair.second?.let {
                PremiereCard(
                    title = FormatDate.formatDate(it),
                    description = pair.first.toNormalText(context)
                )

                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

private fun Premiere.toMap(): Map<String, String?> {
    return mapOf(
        "bluray" to bluray,
        "digital" to digital,
        "dvd" to dvd,
        "russia" to russia,
        "world" to world
    )
}

private fun String.toNormalText(context: Context): String {
    return when (this) {
        "bluray" -> context.getString(R.string.release_blu_ray)
        "digital" -> context.getString(R.string.release_digital)
        "dvd" -> context.getString(R.string.release_dvd)
        "russia" -> context.getString(R.string.release_russia)
        "world" -> context.getString(R.string.release_world)
        else -> context.getString(R.string.unknown)
    }
}
