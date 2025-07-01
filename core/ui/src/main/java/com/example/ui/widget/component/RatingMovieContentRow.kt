package com.example.ui.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.model.totalValue.Rating
import com.example.model.totalValue.Votes
import com.example.ui.widget.chips.RatingCard

@Composable
fun RatingMovieContentRow(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    rating: Rating,
    votes: Votes
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        contentPadding = contentPadding
    ) {
        item {
            if (rating.imdb.isCorrectRating()) {
                RatingCard(
                    title = "Рейтинг IMDb",
                    rating = rating.imdb ?: 0f,
                    votes = votes.imdb ?: 0
                )
            }
        }

        item {
            if (rating.filmCritics.isCorrectRating()) {
                RatingCard(
                    title = "Рейтинг критиков",
                    rating = rating.filmCritics ?: 0f,
                    votes = votes.filmCritics ?: 0
                )
            }
        }

        item {
            if (rating.russianFilmCritics.isCorrectRating()) {
                RatingCard(
                    title = "Рейтинг российских критиков",
                    rating = rating.russianFilmCritics ?: 0f,
                    votes = votes.russianFilmCritics ?: 0
                )
            }
        }
    }
}

private fun Float?.isCorrectRating(): Boolean {
    return this != null && this.toInt() != 0
}