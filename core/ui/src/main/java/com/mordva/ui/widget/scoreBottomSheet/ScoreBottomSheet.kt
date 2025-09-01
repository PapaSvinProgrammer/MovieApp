package com.mordva.ui.widget.scoreBottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.mordva.model.movie.Movie
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.other.toRatingColor
import com.mordva.util.convert.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreBottomSheet(
    movie: Movie,
    onDismissRequest: () -> Unit,
    onValueChange: (Int) -> Unit,
    onSave: (Int) -> Unit
) {
    var scoreValue by remember { mutableIntStateOf(0) }
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
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopHeadlineContent(movie)

            Spacer(modifier = Modifier.height(20.dp))

            ScorePicker {
                scoreValue = it
                onValueChange(it)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                colors = ButtonDefaults.buttonColors(scoreValue.toRatingColor()),
                onClick = {
                    onSave(scoreValue)
                    onDismissRequest()
                }
            ) {
                Text(
                    text = "Поставить оценку",
                    color = Color.White
                )
            }
        }

        DisableChangeStatusBarIconColor()
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