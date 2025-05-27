package com.example.movieapp.app.navigation

import androidx.compose.ui.graphics.Color
import com.example.movieapp.R

sealed class BottomBarTab(
    val title: String,
    val icon: Int,
    val color: Color,
    val route: NavRoute
) {
    data object Account: BottomBarTab(
        title = "Профиль",
        icon = R.drawable.ic_person_fill,
        color = Color(0xFFFFA574),
        route = AccountRoute
    )

    data object Home: BottomBarTab(
        title = "Главная",
        icon = R.drawable.ic_home_fill,
        color = Color(0xFFFA6FFF),
        route = HomeRoute
    )

    data object Search: BottomBarTab(
        title = "Поиск",
        icon = R.drawable.ic_search,
        color = Color(0xFFADFF64),
        route = SearchRoute
    )

    data object Favorite: BottomBarTab(
        title = "Избранное",
        icon = R.drawable.ic_bookmark_fill,
        color = Color(0xFF009688),
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