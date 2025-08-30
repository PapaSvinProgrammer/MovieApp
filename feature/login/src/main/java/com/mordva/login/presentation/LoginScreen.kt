package com.mordva.login.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movieapp.login.R
import com.mordva.login.presentation.widget.CustomButtonWithLoading
import com.mordva.login.presentation.widget.state.AuthState
import com.mordva.navigation.HomeGraph
import com.yandex.authsdk.YandexAuthLoginOptions
import com.yandex.authsdk.YandexAuthOptions
import com.yandex.authsdk.YandexAuthSdk
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource

@Composable
internal fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
    hazeState: HazeState,
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    val yandexSdk = remember { YandexAuthSdk.create(YandexAuthOptions(context)) }
    val yandexLoginOption = remember { YandexAuthLoginOptions() }
    val yandexLauncher = rememberLauncherForActivityResult(yandexSdk.contract) { result ->
        viewModel.handleYandexAuth(result)
    }

    LaunchedEffect(state.yandexAuthState, state.vkAuthState) {
        if (state.yandexAuthState is AuthState.Success || state.vkAuthState is AuthState.Success) {
            navController.navigate(HomeGraph) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    Box {
        Image(
            painter = painterResource(R.drawable.login_start_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .hazeEffect(hazeState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            CustomButtonWithLoading(
                modifier = Modifier.padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(Color.Black),
                isLoading = state.yandexAuthState is AuthState.Loading,
                isEnable = state.vkAuthState !is AuthState.Loading,
                onClick = {
                    viewModel.updateYandexAuthState(AuthState.Loading)
                    yandexLauncher.launch(yandexLoginOption)
                },
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_yandex_logo),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = stringResource(R.string.login_with_yandex),
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomButtonWithLoading(
                modifier = Modifier.padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(Color.Black),
                isLoading = state.vkAuthState is AuthState.Loading,
                isEnable = state.yandexAuthState !is AuthState.Loading,
                onClick = {
                    viewModel.updateVkAuthState(AuthState.Loading)
                    viewModel.authWithVk()
                }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_vk_logo),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = stringResource(R.string.login_with_vk),
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
