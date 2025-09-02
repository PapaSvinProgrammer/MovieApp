package com.mordva.ui.widget.listItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movieapp.ui.R
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.chips.RatingChip

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    imageHeight: Dp = 190.dp,
    imageWidth: Dp = 140.dp,
    name: String,
    image: String,
    rating: Float? = null,
    top250: Int? = null,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = image,
                error = painterResource(R.drawable.ic_movie),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(imageHeight)
                    .width(imageWidth)
                    .clip(RoundedCornerShape(10.dp))
            )

            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                fontSize = Typography.bodySmall.fontSize,
                overflow = TextOverflow.Ellipsis,
                minLines = 2,
                maxLines = 2,
                modifier = Modifier
                    .width(imageWidth)
                    .padding(vertical = 10.dp)
            )
        }

        rating?.let { value ->
            RatingChip(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(5.dp),
                rating = value,
                top = top250
            )
        }
    }
}