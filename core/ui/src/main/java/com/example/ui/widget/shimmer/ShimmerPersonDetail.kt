package com.example.movieapp.ui.widget.shimmer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerPersonDetail(modifier: Modifier = Modifier) {
    LazyColumn {
        items(7) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerText(
                    width = 100.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 10.dp)
                )

                ShimmerText(
                    width = 100.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                )
            }
        }
    }
}