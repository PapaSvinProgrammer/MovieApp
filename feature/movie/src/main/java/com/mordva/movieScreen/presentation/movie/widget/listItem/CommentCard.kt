package com.mordva.movieScreen.presentation.movie.widget.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.model.movie.Comment
import com.mordva.ui.theme.Green
import com.mordva.util.convert.FormatDate

@Composable
internal fun CommentCard(comment: Comment) {
    Column(
        modifier = Modifier
            .width(320.dp)
            .height(200.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        TopInformation(comment)
        CommentContent(comment)
    }
}

@Composable
private fun TopInformation(comment: Comment) {
    Row(
        modifier = Modifier.padding(top = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val color = when (comment.type) {
            "Позитивный" -> Green
            "Нейтральный" -> Color.Gray
            "Негативный" -> Color.Red
            else -> MaterialTheme.colorScheme.surfaceContainer
        }

        Box(
            modifier = Modifier
                .height(50.dp)
                .width(4.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(10.dp)
                )
        )

        Column(
            modifier = Modifier.padding(start = 15.dp)
        ) {
            Text(
                text = comment.author,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = FormatDate.formatDate(comment.date),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CommentContent(comment: Comment) {
    Column(
        modifier = Modifier
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 15.dp
            )
    ) {
        Text(
            text = comment.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = comment.review,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )
    }
}