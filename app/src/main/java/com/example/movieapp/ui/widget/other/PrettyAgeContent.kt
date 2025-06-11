package com.example.movieapp.ui.widget.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrettyAgeContent(
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    date: String,
    age: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = date,
            fontSize = 14.sp,
            fontWeight = fontWeight
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 7.dp)
                .size(3.dp)
                .background(
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = CircleShape
                )
        )

        Text(
            text = age,
            fontSize = 14.sp,
            fontWeight = fontWeight
        )
    }
}