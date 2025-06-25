package com.example.movieapp.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.ui.widget.other.TitleTopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(R.string.about_app))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

    }
}