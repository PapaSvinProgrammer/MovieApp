package com.example.movieapp.app.navigation

import androidx.compose.ui.graphics.Color
import com.example.movieapp.R
import com.example.movieapp.ui.theme.ColorGradient1
import com.example.movieapp.ui.theme.ColorGradient2
import com.example.movieapp.ui.theme.ColorGradient3
import com.example.movieapp.ui.theme.ColorGradient4

sealed class BottomBarTab(
    val title: String,
    val icon: Int,
    val color: Color,
    val route: NavRoute
) {
    data object Account: BottomBarTab(
        title = "Профиль",
        icon = R.drawable.ic_person_fill,
        color = ColorGradient1,
        route = AccountRoute
    )

    data object Home: BottomBarTab(
        title = "Главная",
        icon = R.drawable.ic_home_fill,
        color = ColorGradient2,
        route = HomeRoute
    )

    data object Search: BottomBarTab(
        title = "Поиск",
        icon = R.drawable.ic_search,
        color = ColorGradient3,
        route = SearchRoute
    )

    data object Favorite: BottomBarTab(
        title = "Избранное",
        icon = R.drawable.ic_bookmark_fill,
        color = ColorGradient4,
        route = FavoriteRoute
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