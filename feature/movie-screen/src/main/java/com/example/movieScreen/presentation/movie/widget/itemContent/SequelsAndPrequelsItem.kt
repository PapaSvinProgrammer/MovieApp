package com.example.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.model.movie.Movie
import com.example.movieapp.ui.R
import com.example.navigationroute.MovieRoutes
import com.example.ui.widget.lazyComponent.DefaultLazyRow
import com.example.ui.widget.listItems.MovieCard

internal fun LazyListScope.sequelsAndPrequelsItem(
    list: List<Movie>,
    navController: NavController
) {
    item {
        if (list.isEmpty()) return@item

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.sequals_and_prequals),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )

        DefaultLazyRow(
            list = list,
            lastItemCard = {},
        ) {
            MovieCard(
                movie = it,
                onClick = {
                    navController.navigate(MovieRoutes.MovieRoute(it.id))
                }
            )
        }
    }
}
