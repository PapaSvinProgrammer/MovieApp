package com.mordva.ui.widget.scoreBottomSheet

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordva.ui.widget.other.toRatingColor
import kotlin.math.abs
import kotlin.math.min

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ScorePicker(
    modifier: Modifier = Modifier,
    range: IntRange = 1..10,
    onValueChange: (Int) -> Unit
) {
    val items = range.toList()

    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(
        lazyListState = listState,
        snapPosition = SnapPosition.Center
    )


    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var selectedIndex by remember { mutableIntStateOf(0) }

    Box(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(80.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
        )

        LazyRow(
            state = listState,
            flingBehavior = flingBehavior,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = (screenWidth / 2) - 40.dp)
        ) {
            itemsIndexed(items) { index, item ->
                val center by remember { derivedStateOf { listState.getCenterItemIndex() ?: 0 } }
                selectedIndex = center
                onValueChange(items[center])

                val isFocused = index == selectedIndex
                val distance = abs(index - selectedIndex).toFloat()
                val norm = min(1f, distance / 3f)
                val targetScale = if (isFocused) 1.6f else 1f - 0.2f * norm
                val targetAlpha = if (isFocused) 1f else 0.5f

                val scale by animateFloatAsState(targetScale)
                val alpha by animateFloatAsState(targetAlpha)

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .scale(scale)
                        .alpha(alpha),
                    contentAlignment = Alignment.Center
                ) {
                    ScoreText(
                        isFocused = isFocused,
                        value = item
                    )
                }
            }
        }
    }
}


@Composable
internal fun ScoreText(
    isFocused: Boolean,
    value: Int
) {
    if (isFocused) {
        Text(
            text = value.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = value.toRatingColor()
        )
    } else {
        Text(
            text = value.toString(),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

private fun LazyListState.getCenterItemIndex(): Int? {
    val layoutInfo = this.layoutInfo
    val visibleItems = layoutInfo.visibleItemsInfo

    if (visibleItems.isEmpty()) return null
    val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset + 1) / 2

    return visibleItems.minByOrNull { item ->
        val itemCenter = (item.offset + item.size) / 2
        abs(itemCenter - viewportCenter)
    }?.index
}
