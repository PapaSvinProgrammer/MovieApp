package com.example.movieapp.app.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import com.example.movieapp.R

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

object FadingDefaults {
    val bottomFade = Brush.verticalGradient(0.7f to Color.Red, 1f to Color.Transparent)
}

data class ApplicationIcon(
    val index: Int,
    val image: Int,
    val origColor: Boolean
)

val collectionCategoryList = listOf(
    "Онлайн-кинотеатр",
    "Премии",
    "Сборы",
    "Фильмы",
    "Сериалы"
)

val iconsApplication = listOf(
    ApplicationIcon(
        index = 1,
        image = R.drawable.ic_launcher_default,
        origColor = false
    ),
    ApplicationIcon(
        index = 2,
        image = R.drawable.ic_logo2,
        origColor = true
    ),
    ApplicationIcon(
        index = 3,
        image = R.drawable.ic_logo3,
        origColor = false
    ),
    ApplicationIcon(
        index = 4,
        image = R.drawable.ic_logo4,
        origColor = true
    )
)

val categoriesFilmography = mapOf(
    "actor" to "Актер",
    "producer" to "Продюсер",
    "cameo" to "Играет самого себя",
    "uncredited" to "Не указан в титрах"
)