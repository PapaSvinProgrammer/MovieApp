package com.mordva.ui.widget.navigation

import androidx.compose.ui.graphics.Color
import com.example.movieapp.ui.R
import com.mordva.navigation.AccountGraph
import com.mordva.navigation.FavoriteGraph
import com.mordva.navigation.HomeGraph
import com.mordva.navigation.RootGraph
import com.mordva.navigation.SearchGraph
import com.mordva.ui.theme.ColorGradient1
import com.mordva.ui.theme.ColorGradient2
import com.mordva.ui.theme.ColorGradient3
import com.mordva.ui.theme.ColorGradient4

sealed class BottomBarTab(
    val title: String,
    val icon: Int,
    val color: Color,
    val route: RootGraph
) {
    data object Account: BottomBarTab(
        title = "Профиль",
        icon = R.drawable.ic_person_fill,
        color = ColorGradient1,
        route = AccountGraph
    )

    data object Home: BottomBarTab(
        title = "Главная",
        icon = R.drawable.ic_home_fill,
        color = ColorGradient2,
        route = HomeGraph
    )

    data object Search: BottomBarTab(
        title = "Поиск",
        icon = R.drawable.ic_search,
        color = ColorGradient3,
        route = SearchGraph
    )

    data object Favorite: BottomBarTab(
        title = "Избранное",
        icon = R.drawable.ic_bookmark_fill,
        color = ColorGradient4,
        route = FavoriteGraph
    )
}

object BottomBarItems {
    val items = listOf(
        BottomBarTab.Home,
        BottomBarTab.Favorite,
        BottomBarTab.Search,
        BottomBarTab.Account
    )
}