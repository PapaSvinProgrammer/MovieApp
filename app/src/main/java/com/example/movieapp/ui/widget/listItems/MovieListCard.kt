package com.example.movieapp.ui.widget.listItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.utils.ConvertData
import com.example.movieapp.R
import com.example.movieapp.ui.widget.chips.RatingChip
import com.example.network.module.movie.Movie

@Composable
fun MovieListCard(
    movie: Movie,
    onClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(15.dp)
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.poster?.url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                error = painterResource(R.drawable.ic_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(130.dp)
                    .width(90.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            RatingChip(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp)),
                rating = movie.rating?.kp ?: 0f,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        Box(
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                NameContent(movie)
                DetailInfoContent(movie)
            }

            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = onSettingsClick
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.rotate(90f)
                )
            }
        }
    }
}

@Composable
private fun NameContent(movie: Movie) {
    Text(
        text = movie.name ?: "",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )

    Text(
        text = getAlternativeName(movie),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

private fun getAlternativeName(movie: Movie): String {
    var res = ""

    movie.alternativeName?.let {
        if (it.isNotEmpty()) res += "$it, "
    }

    res += ConvertData.convertDateCreated(movie.year, movie.releaseYears)

    return res
}

@Composable
private fun DetailInfoContent(movie: Movie) {
    Text(
        text = movie.countries.map { it.name }.joinToString(", "),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )

    Text(
        text = movie.genres.map { it.name }.joinToString(", "),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}