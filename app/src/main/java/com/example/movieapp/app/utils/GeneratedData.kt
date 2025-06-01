package com.example.movieapp.app.utils

import androidx.compose.ui.res.stringResource
import com.example.movieapp.R

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