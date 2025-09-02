package com.mordva.ui.widget.scoreBottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat
import coil3.compose.AsyncImage
import com.example.movieapp.ui.R
import com.mordva.model.local.RatedMovie
import com.mordva.model.movie.Movie
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.MovieCard
import com.mordva.ui.widget.other.toRatingColor
import com.mordva.ui.widget.shimmer.ShimmerMovieRow
import com.mordva.util.convert.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreBottomSheet(
    movie: Movie,
    ratedMovieState: RatedMovieState = RatedMovieState.Init,
    initialValue: Int? = null,
    onDismissRequest: () -> Unit,
    onAction: (ScoreSheetAction) -> Unit
) {
    var scoreValue by remember { mutableStateOf<Int?>(initialValue ?: 5) }
    val containerColor = scoreValue?.toRatingColor() ?: MaterialTheme.colorScheme.onSurfaceVariant
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 70.dp),
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopHeadlineContent(movie)

            Spacer(modifier = Modifier.height(20.dp))

            ScorePicker(
                initialValue = initialValue ?: 5,
                onValueChange = { score ->
                    scoreValue = score
                    score?.let { onAction(ScoreSheetAction.ValueChange(it)) }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                colors = ButtonDefaults.buttonColors(containerColor),
                onClick = {
                    if (scoreValue == null && initialValue == null) {
                        onAction(ScoreSheetAction.Nothing)
                    } else if (scoreValue == null) {
                        onAction(ScoreSheetAction.Delete)
                    } else {
                        onAction(ScoreSheetAction.Save(scoreValue ?: -1))
                    }

                    onDismissRequest()
                }
            ) {
                val text = if (initialValue == null && scoreValue != null) {
                    stringResource(R.string.set_rating)
                } else if (initialValue == null) {
                    stringResource(R.string.not_change)
                } else if (scoreValue == null) {
                    stringResource(R.string.delete_rating)
                } else {
                    stringResource(R.string.change_rating)
                }

                Text(
                    text = text,
                    color = Color.White
                )
            }

            scoreValue?.let {
                RenderRatedMovieList(
                    state = ratedMovieState,
                    currentRating = it,
                    onClick = {}
                )
            }
        }

        DisableChangeStatusBarIconColor()
    }
}

@Composable
private fun ColumnScope.RenderRatedMovieList(
    state: RatedMovieState,
    currentRating: Int,
    onClick: (RatedMovie) -> Unit
) {
    when (state) {
        is RatedMovieState.Error -> {}
        RatedMovieState.Init -> {}
        RatedMovieState.Loading -> ShimmerMovieRow()
        is RatedMovieState.Success -> {
            if (state.data.isEmpty()) return

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(15.dp),
                text = "Ваши фильмы на $currentRating",
                fontSize = Typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Medium
            )

            DefaultLazyRow(
                list = state.data,
                key = { it.movieId },
                content = { ratedMovie ->
                    MovieCard(
                        name = ratedMovie.name,
                        image = ratedMovie.poster,
                        onClick = { onClick(ratedMovie) }
                    )
                }
            )
        }
    }
}

@Composable
private fun TopHeadlineContent(movie: Movie) {
    Text(
        text = stringResource(R.string.evaluate),
        fontSize = Typography.bodyMedium.fontSize
    )

    Spacer(modifier = Modifier.height(10.dp))

    AsyncImage(
        model = movie.poster?.url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(190.dp)
            .width(140.dp)
            .clip(RoundedCornerShape(10.dp))
    )

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = movie.name ?: "",
        fontSize = Typography.bodyMedium.fontSize,
        fontWeight = FontWeight.Medium
    )

    Text(
        text = ConvertData.convertDateCreated(
            year = movie.year,
            releaseYears = movie.releaseYears
        ),
        fontSize = Typography.bodyMedium.fontSize,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun DisableChangeStatusBarIconColor() {
    val view = LocalView.current
    (view.parent as? DialogWindowProvider)?.window?.let { window ->
        SideEffect {
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }
}
