package com.mordva.movieScreen.presentation.movie.widget.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mordva.ui.theme.Typography

@Composable
internal fun PremiereCard(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(15.dp)
    ) {
        Text(
            text = title,
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = description,
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}