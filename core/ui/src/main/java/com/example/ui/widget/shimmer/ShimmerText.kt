package com.example.ui.widget.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.widget.shimmer.shimmerEffect

@Composable
fun ShimmerText(
    modifier: Modifier = Modifier,
    height: Dp = 12.dp,
    width: Dp = 100.dp
) {
    Box(
        modifier = modifier
            .size(height = height, width = width)
            .shimmerEffect()
    )
}