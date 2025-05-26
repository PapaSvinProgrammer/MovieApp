package com.example.movieapp.app.navigation

import com.example.movieapp.R

data class NavBarItem(
    val title: String,
    val route: NavRoute,
    val icon: Int,
    val iconFill: Int
)

object BottomBarItems {
    val items = listOf(
        NavBarItem(
            title = "Главная",
            icon = R.drawable.ic_home,
            iconFill = R.drawable.ic_home_fill,
            route = HomeRoute
        ),
        NavBarItem(
            title = "Избранное",
            icon = R.drawable.ic_bookmark,
            iconFill = R.drawable.ic_bookmark_fill,
            route = FavoriteRoute
        ),
        NavBarItem(
            title = "Поиск",
            icon = R.drawable.ic_search,
            iconFill = R.drawable.ic_search,
            route = SearchRoute
        ),
        NavBarItem(
            title = "Аккаунт",
            icon = R.drawable.ic_person,
            iconFill = R.drawable.ic_person_fill,
            route = AccountRoute
        )
    )
}