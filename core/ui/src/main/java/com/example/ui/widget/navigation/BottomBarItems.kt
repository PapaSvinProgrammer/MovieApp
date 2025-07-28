package com.example.ui.widget.navigation

import androidx.compose.ui.graphics.Color
import com.example.movieapp.ui.R
import com.example.navigationroute.MainRoutes
import com.example.ui.theme.ColorGradient1
import com.example.ui.theme.ColorGradient2
import com.example.ui.theme.ColorGradient3
import com.example.ui.theme.ColorGradient4

sealed class BottomBarTab(
    val title: String,
    val icon: Int,
    val color: Color,
    val route: com.example.navigationroute.NavRoute
) {
    data object Account: BottomBarTab(
        title = "Профиль",
        icon = R.drawable.ic_person_fill,
        color = ColorGradient1,
        route = MainRoutes.AccountRoute
    )

    data object Home: BottomBarTab(
        title = "Главная",
        icon = R.drawable.ic_home_fill,
        color = ColorGradient2,
        route = MainRoutes.HomeRoute
    )

    data object Search: BottomBarTab(
        title = "Поиск",
        icon = R.drawable.ic_search,
        color = ColorGradient3,
        route = MainRoutes.SearchRoute
    )

    data object Favorite: BottomBarTab(
        title = "Избранное",
        icon = R.drawable.ic_bookmark_fill,
        color = ColorGradient4,
        route = MainRoutes.FavoriteRoute
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