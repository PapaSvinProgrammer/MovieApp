package com.example.movieapp.ui.widget.collapsingTopBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.utils.ConvertData
import com.example.movieapp.R
import com.example.movieapp.ui.widget.component.isCorrectUrl
import com.example.network.module.movie.Movie

@Composable
fun ExpandedContent(movie: Movie) {
    val date = ConvertData.convertDateCreated(movie.year, movie.releaseYears)
    val genres = movie.genres.take(2).map { it.name }.joinToString(", ")
    val countries = movie.countries.take(2).map { it.name }.joinToString(", ")
    var length = ConvertData.getPrettyLength(movie.movieLength ?: 0)
    val age = ConvertData.getPrettyAgeRating(movie.ageRating ?: 0)

    if (movie.isSeries == true) {
        val seasonCount = movie.seasonsInfo?.count { it.number != 0 } ?: 0
        length = ConvertData.getPrettyCountSeasons(seasonCount)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (movie.backdrop?.url.isCorrectUrl() && movie.logo?.url.isCorrectUrl()) {
            ContentWithBackdrop(
                movie = movie,
                genres = genres,
                date = date,
                countries = countries,
                length = length,
                age = age
            )
        }
        else {
            ContentWithoutBackdrop(
                movie = movie,
                genres = genres,
                date = date,
                countries = countries,
                length = length,
                age = age
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        ExpandedNavigationBar()
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()
    }
}

@Composable
private fun ContentWithBackdrop(
    movie: Movie,
    date: String,
    genres: String,
    countries: String,
    length: String,
    age: String
) {
    Spacer(modifier = Modifier.height(360.dp))

    ExpandedBasicContent(
        rating = movie.rating?.kp ?: 0f,
        votes = movie.votes?.kp ?: 0,
        top250 = movie.top250,
        date = date,
        genres = genres,
        countries = countries,
        length = length,
        age = age,
        title = {
            MovieLogo(
                url = movie.logo?.url,
                name = movie.name ?: ""
            )
        }
    )
}

@Composable
private fun ContentWithoutBackdrop(
    movie: Movie,
    date: String,
    genres: String,
    countries: String,
    length: String,
    age: String
) {
    Spacer(modifier = Modifier.height(100.dp))

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.poster?.url)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_movie),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.height(300.dp).width(200.dp).clip(RoundedCornerShape(10.dp))
    )

    Spacer(modifier = Modifier.height(20.dp))

    ExpandedBasicContent(
        rating = movie.rating?.kp ?: 0f,
        votes = movie.votes?.kp ?: 0,
        top250 = movie.top250,
        date = date,
        genres = genres,
        countries = countries,
        length = length,
        age = age,
        title = {
            MovieLogo(
                url = null,
                name = movie.name ?: ""
            )
        }
    )
}

@Composable
internal fun MovieLogo(url: String?, name: String) {
    if (url != null) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_movie),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 55.dp)
        )
    }
    else {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}