package com.mordva.settings.presentation.widget.state

import com.example.movieapp.ui.R

internal data class ApplicationIcon(
    val index: Int,
    val image: Int,
    val origColor: Boolean
)

internal val iconsApplication = listOf(
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