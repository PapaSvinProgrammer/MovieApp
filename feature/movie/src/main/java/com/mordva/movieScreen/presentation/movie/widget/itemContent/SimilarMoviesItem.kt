package com.mordva.movieScreen.presentation.movie.widget.itemContent

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
import com.example.movieapp.ui.R
import com.mordva.model.movie.Movie
import com.mordva.navigation.MovieGraph
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.MovieCard

internal fun LazyListScope.similarMoviesItem(
    similarMovies: List<Movie>,
    navController: NavController
) {
    item {
        if (similarMovies.isEmpty()) return@item

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.similar_movies),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )

        DefaultLazyRow(
            list = similarMovies,
            lastItemCard = {},
        ) {
            MovieCard(
                movie = it,
                onClick = {
                    navController.navigate(MovieGraph.MovieRoute(it.id))
                }
            )
        }
    }
}