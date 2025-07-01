package com.example.movieapp.ui.widget.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerMovieDetailList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(7) {
            ShimmerMovieDetailCard()
        }
    }
}

@Composable
fun ShimmerMovieDetailCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.width(15.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            ShimmerNameContent()

            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                ShimmerText(width = 110.dp)
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerOtherInfoContent()
            }
        }
    }
}

@Composable
private fun ShimmerNameContent() {
    Column {
        ShimmerText(width = 280.dp)
        Spacer(modifier = Modifier.height(8.dp))
        ShimmerText(width = 130.dp)
    }
}

@Composable
private fun ShimmerOtherInfoContent() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f)) {
            ShimmerText(width = 40.dp)
            Spacer(modifier = Modifier.width(10.dp))
            ShimmerText(width = 40.dp)
        }

        ShimmerText(width = 60.dp)
    }
}