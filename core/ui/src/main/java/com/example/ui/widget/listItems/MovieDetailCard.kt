package com.example.ui.widget.listItems

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.model.category.ItemName
import com.example.model.movie.Movie
import com.example.movieapp.ui.R
import com.example.ui.widget.other.RatingText
import com.example.utils.ConvertData
import com.example.utils.PrettyData

@Composable
fun MovieDetailCard(
    movie: Movie,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable(onClick = onClick)
            .padding(15.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.poster?.url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            error = painterResource(R.drawable.ic_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(100.dp)
                .width(85.dp)
                .clip(RoundedCornerShape(5.dp))
        )

        Spacer(modifier = Modifier.width(15.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            DetailInfoContent(
                title = movie.name ?: "",
                subtitle = ConvertData.getAlternativeNameForMovie(movie)
            )

            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                GenreContent(movie.genres)

                OtherInfoContent(
                    rating = movie.rating?.kp ?: 0f,
                    votes = movie.votes?.kp ?: 0,
                    country = movie.countries
                )
            }
        }
    }
}

@Composable
private fun BoxScope.DetailInfoContent(
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier.fillMaxWidth().align(Alignment.TopStart)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = subtitle,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun GenreContent(genre: List<ItemName>) {
    Text(
        text = genre.joinToString(", ") { it.name },
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@SuppressLint("DefaultLocale")
@Composable
private fun OtherInfoContent(
    rating: Float,
    votes: Int,
    country: List<ItemName>
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.weight(1f)) {
            RatingText(rating)
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = PrettyData.getPrettyInt(votes),
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = country.joinToString(", ") { it.name },
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth().weight(2f)
        )
    }
}