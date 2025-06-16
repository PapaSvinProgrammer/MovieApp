package com.example.movieapp.ui.widget.collapsingTopBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun CollapsedTopBar(
    isCollapsed: Boolean,
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    val color: Color by animateColorAsState(
        if (isCollapsed) {
            MaterialTheme.colorScheme.background
        } else {
            Color.Transparent
        }
    )

    Box(
        modifier = Modifier
            .zIndex(2f)
            .background(color)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            NavigationContent { navigationIcon() }
            TitleContent(
                content = title,
                isCollapsed = isCollapsed
            )
            ActionContent { actions() }
        }
    }
}

@Composable
private fun BoxScope.NavigationContent(content: @Composable () -> Unit) {
    Box(modifier = Modifier.align(Alignment.CenterStart)) {
        content()
    }
}

@Composable
private fun BoxScope.TitleContent(
    content: @Composable () -> Unit,
    isCollapsed: Boolean
) {
    AnimatedVisibility(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(horizontal = 50.dp),
        visible = isCollapsed,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        content()
    }
}

@Composable
private fun BoxScope.ActionContent(content: @Composable RowScope.() -> Unit) {
    Row(modifier = Modifier.align(Alignment.Center)) {
        content()
    }
}