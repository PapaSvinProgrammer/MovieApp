package com.example.movieScreen.presentation.widget.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.R
import com.example.ui.widget.other.RatingText
import com.example.utils.convert.PrettyData


@Composable
internal fun RatingCardLarge(
    modifier: Modifier = Modifier,
    title: String,
    rating: Float,
    votes: Int,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(vertical = 15.dp)
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RatingText(
                    fontSize = 55.sp,
                    rating = rating
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${PrettyData.getPrettyInt(votes)} оценок",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(170.dp),
                    onClick = onClick
                ) {
                    Text(
                        text = stringResource(R.string.evaluate),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}