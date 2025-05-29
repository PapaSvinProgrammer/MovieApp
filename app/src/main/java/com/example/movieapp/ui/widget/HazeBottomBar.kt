package com.example.movieapp.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.movieapp.app.navigation.BottomBarItems
import com.example.movieapp.app.navigation.BottomBarTab
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

@Composable
fun HazeBottomBar(
    tabs: List<BottomBarTab>,
    navController: NavHostController,
    hazeState: HazeState,
    visible: Boolean
) {
    var selectedTab by remember {
        mutableIntStateOf(
            BottomBarItems.items.indexOf(BottomBarTab.Home)
        )
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 54.dp)
                .navigationBarsPadding()
                .fillMaxWidth()
                .height(64.dp)
                .border(
                    width = Dp.Hairline,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = .8f),
                            Color.White.copy(alpha = .2f),
                        ),
                    ),
                    shape = CircleShape
                )
                .hazeChild(state = hazeState, shape = CircleShape)
        ) {
            NavigationBottomTabs(
                tabs = tabs,
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = BottomBarItems.items.indexOf(it)

                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

            val animatedSelectedTabIndex by animateFloatAsState(
                targetValue = selectedTab.toFloat(),
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioLowBouncy,
                )
            )

            val animatedColor by animateColorAsState(
                targetValue = tabs[selectedTab].color,
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                )
            )

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            ) {
                val tabWidth = size.width / tabs.size
                drawCircle(
                    color = animatedColor.copy(alpha = .6f),
                    radius = size.height / 2,
                    center = Offset(
                        (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                        size.height / 2
                    )
                )
            }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            ) {
                val path = androidx.compose.ui.graphics.Path().apply {
                    addRoundRect(RoundRect(size.toRect(), CornerRadius(size.height)))
                }
                val length = PathMeasure().apply { setPath(path, false) }.length

                val tabWidth = size.width / tabs.size
                drawPath(
                    path,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            animatedColor.copy(alpha = 0f),
                            animatedColor.copy(alpha = 1f),
                            animatedColor.copy(alpha = 1f),
                            animatedColor.copy(alpha = 0f),
                        ),
                        startX = tabWidth * animatedSelectedTabIndex,
                        endX = tabWidth * (animatedSelectedTabIndex + 1),
                    ),
                    style = Stroke(
                        width = 6f,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(length / 2, length)
                        )
                    )
                )
            }
        }
    }
}

@Composable
private fun NavigationBottomTabs(
    tabs: List<BottomBarTab>,
    selectedTab: Int,
    onTabSelected: (BottomBarTab) -> Unit
) {
    CompositionLocalProvider(
        LocalTextStyle provides  LocalTextStyle.current.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        ),
        LocalContentColor provides Color.White
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            tabs.forEach { tab ->
                val alpha by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .35f
                )

                val scale by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .98f,
                    visibilityThreshold = .000001f,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy
                    )
                )

                Column(
                    modifier = Modifier
                        .scale(scale)
                        .alpha(alpha)
                        .fillMaxHeight()
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures { onTabSelected(tab) }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = null
                    )

                    //Text(text = tab.title)
                }
            }
        }
    }
}