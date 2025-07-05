package com.example.ui.widget.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerPersonContent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(210.dp)
                .clip(RoundedCornerShape(10.dp))
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column {
            ShimmerText(width = 200.dp, height = 40.dp)
            Spacer(modifier = Modifier.height(10.dp))
            ShimmerText(width = 150.dp)

            Spacer(modifier = Modifier.height(20.dp))

            ShimmerText(width = 110.dp)
            Spacer(modifier = Modifier.height(10.dp))
            ShimmerText(width = 90.dp)
            Spacer(modifier = Modifier.height(10.dp))
            ShimmerText(width = 100.dp)
        }
    }
}