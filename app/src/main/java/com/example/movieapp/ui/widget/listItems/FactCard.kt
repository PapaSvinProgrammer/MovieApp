package com.example.movieapp.ui.widget.listItems

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

@Composable
fun FactCard(
    modifier: Modifier = Modifier,
    text: String,
    isSpoiler: Boolean,
    onClick: () -> Unit
) {
    var isChecked by remember { mutableStateOf(!isSpoiler) }

    Box(
        modifier = modifier
            .width(300.dp)
            .height(160.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                if (isChecked) {
                    onClick()
                } else {
                    isChecked = true
                }
            }
            .padding(15.dp)
    ) {
        AnimatedContent(
            targetState = isChecked
        ) { targetState ->
            if (targetState) {
                Text(
                    text = text,
                    fontSize = 13.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
            else {
                SpoilerContent()
            }
        }
    }
}

@Composable
private fun SpoilerContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_visibility_off),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(R.string.spoilers_title),
                fontSize = 13.sp
            )

            Text(
                text = stringResource(R.string.spoilers_description),
                fontSize = 13.sp
            )
        }
    }
}