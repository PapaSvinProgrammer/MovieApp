package com.mordva.movieScreen.presentation.movie.widget.listItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movieapp.ui.R
import com.mordva.model.person.PersonMovie
import com.mordva.ui.theme.Typography

@Composable
internal fun PersonMovieListItem(
    modifier: Modifier = Modifier,
    person: PersonMovie
) {
    Row(
        modifier = modifier
            .width(290.dp)
            .padding(
                horizontal = 15.dp,
                vertical = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = person.photo,
            contentDescription = null,
            error = painterResource(R.drawable.ic_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(80.dp)
                .width(60.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column {
            Text(
                text = person.name ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = Typography.bodyMedium.fontSize
            )

            Text(
                text = person.description ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = Typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}