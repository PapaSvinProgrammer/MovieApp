package com.example.movieapp.ui.widget.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.movieapp.ui.widget.listItams.SearchMovieCard
import com.example.network.module.movie.Movie
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun SearchContent(
    list: List<Movie>,
    hazeState: HazeState,
    onClick: (Int) -> Unit,
    onLoadMore: () -> Unit
) {
    EndlessLazyColumn(
        items = list,
        loadMore = onLoadMore,
        modifier = Modifier.haze(hazeState)
    ) {
        SearchMovieCard(it) { onClick(it.id) }
        HorizontalDivider(modifier = Modifier.padding(start = 110.dp))
    }
}

@Composable
fun ErrorSearchContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            modifier = Modifier.padding(vertical = 60.dp),
            text = "(^_^)",
            fontSize = 120.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = stringResource(R.string.not_found),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun LoadingSearchContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 40.dp)
        )
    }
}

@Composable
fun SearchHistoryContent() {

}