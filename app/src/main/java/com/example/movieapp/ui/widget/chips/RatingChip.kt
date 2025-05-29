package com.example.movieapp.ui.widget.chips

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.Gold
import com.example.movieapp.ui.theme.Green

@SuppressLint("DefaultLocale")
@Composable
fun RatingChip(
    modifier: Modifier = Modifier,
    rating: Float,
    top: Int? = null
) {
    if (top != null) {
        EliteBox(
            modifier = modifier,
            rating = rating
        )
        return
    }

    if (rating.toInt() >= 7) {
        DefaultBox(
            modifier = modifier,
            rating = rating,
            color = Green
        )
    }
    else if (rating.toInt() >= 5) {
        DefaultBox(
            modifier = modifier,
            rating = rating,
            color = Color.Gray
        )
    }
    else {
        DefaultBox(
            modifier = modifier.background(Color.Red),
            rating = rating,
            color = Color.Red
        )
    }
}

@Preview
@SuppressLint("DefaultLocale")
@Composable
private fun EliteBox(
    modifier: Modifier = Modifier,
    rating: Float = 9.0f
) {
    Box(
        modifier = modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Gold)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_branch),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterStart)
                .padding(start = 5.dp)
                .scale(scaleX = -1f, scaleY = 1f)
        )

        Text(
            text = String.format("%.1f", rating),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 15.dp)
        )

        Icon(
            painter = painterResource(R.drawable.ic_branch),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 5.dp)
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
private fun DefaultBox(
    modifier: Modifier,
    rating: Float,
    color: Color
) {
    Box(
        modifier = modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
    ) {
        Text(
            text = String.format("%.1f", rating),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}