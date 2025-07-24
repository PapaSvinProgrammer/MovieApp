package com.example.ui.widget.navigation

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.Path
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
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials

private val routeList = listOf(
    com.example.navigationroute.HomeRoute::class.java.canonicalName,
    com.example.navigationroute.FavoriteRoute::class.java.canonicalName,
    com.example.navigationroute.SearchRoute::class.java.canonicalName,
    com.example.navigationroute.AccountRoute::class.java.canonicalName
)

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun HazeBottomBar(
    tabs: List<BottomBarTab>,
    navController: NavHostController,
    hazeState: HazeState,
    visible: Boolean
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    var selectedTab by remember { mutableIntStateOf(0) }

    LaunchedEffect(currentRoute) {
        currentRoute?.let {
            selectedTab = routeList.indexOf(it)
        }
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
                            MaterialTheme.colorScheme.onSurface.copy(alpha = .8f),
                            MaterialTheme.colorScheme.onSurface.copy(alpha = .2f),
                        ),
                    ),
                    shape = CircleShape
                )
                .clip(CircleShape)
                .hazeEffect(
                    state = hazeState,
                    style = HazeMaterials.ultraThin()
                ) {
                    blurRadius = 20.dp
                }
        ) {
            NavigationBottomTabs(
                tabs = tabs,
                currentRoute = currentRoute ?: "",
                onTabSelected = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

            BackgroundContent(
                tabs = tabs,
                selectedTab = selectedTab
            )
        }
    }
}

@Composable
private fun BackgroundContent(
    tabs: List<BottomBarTab>,
    selectedTab: Int
) {
    if (selectedTab < 0) return

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
        val path = Path().apply {
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

@Composable
private fun NavigationBottomTabs(
    tabs: List<BottomBarTab>,
    currentRoute: String,
    onTabSelected: (BottomBarTab) -> Unit
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        ),
        LocalContentColor provides MaterialTheme.colorScheme.onSurface
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            tabs.forEach { tab ->
                val isSelected = currentRoute == tab.route::class.java.canonicalName

                val alpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else .35f
                )

                val scale by animateFloatAsState(
                    targetValue = if (isSelected) 1f else .98f,
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
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}