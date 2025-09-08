package com.mordva.ui.widget.listItems

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movieapp.ui.R
import com.mordva.model.movie.Movie
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.chips.RatingChip
import com.mordva.util.convert.ConvertData

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
                model = movie.poster?.url,
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
                top = movie.top250,
                fontSize = Typography.bodySmall.fontSize
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
                DetailSearchInfoContent(movie)
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
        fontSize = Typography.bodyMedium.fontSize,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )

    Text(
        text = getAlternativeName(movie),
        fontSize = Typography.bodySmall.fontSize,
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
private fun DetailSearchInfoContent(movie: Movie) {
    Text(
        text = movie.countries.joinToString(", ") { it.name },
        fontSize = Typography.bodyMedium.fontSize,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )

    Text(
        text = movie.genres.joinToString(", ") { it.name },
        fontSize = Typography.bodyMedium.fontSize,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}