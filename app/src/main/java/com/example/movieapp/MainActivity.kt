package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.viewmodelfactory.ViewModelFactory
import com.example.movieapp.navigation.BottomBarItems
import com.example.movieapp.navigation.HazeBottomBar
import com.example.movieapp.navigation.NavigationGraph
import com.example.navigationroute.NavRoute
import com.example.navigationroute.SearchSettingsRoute
import com.example.ui.theme.MovieAppTheme
import dev.chrisbanes.haze.rememberHazeState
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)

        viewModel.getDarkThemeState()
        viewModel.getEntryState()

        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme(darkTheme = viewModel.darkTheme) {
                val startRoute = if (viewModel.isEntry) com.example.navigationroute.HomeRoute else com.example.navigationroute.StartRoute
                val context = LocalContext.current as ComponentActivity

                DisposableEffect(viewModel.darkTheme) {
                    context.enableEdgeToEdge(
                        statusBarStyle = getSystemBarStyle(viewModel.darkTheme),
                        navigationBarStyle = getSystemBarStyle(viewModel.darkTheme)
                    )

                    onDispose {  }
                }

                MainScreen(
                    startRoute = startRoute
                )
            }
        }
    }

    private fun getSystemBarStyle(isDark: Boolean): SystemBarStyle {
        return when (isDark) {
            true -> SystemBarStyle.dark(Color.Transparent.toArgb())
            false -> SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    startRoute: NavRoute
) {
    var bottomBarVisible by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    bottomBarIsVisibility(
        route = currentRoute,
        onResult = { bottomBarVisible = it }
    )

    val hazeState = rememberHazeState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            HazeBottomBar(
                tabs = BottomBarItems.items,
                hazeState = hazeState,
                navController = navController,
                visible = bottomBarVisible
            )
        }
    ) { _ ->
        NavigationGraph(
            navController = navController,
            startRoute = startRoute,
            hazeState = hazeState
        )
    }
}

private fun bottomBarIsVisibility(route: String?, onResult: (Boolean) -> Unit) {
    when (route) {
        SearchSettingsRoute::class.java.canonicalName -> onResult(false)
        else -> onResult(true)
    }
}