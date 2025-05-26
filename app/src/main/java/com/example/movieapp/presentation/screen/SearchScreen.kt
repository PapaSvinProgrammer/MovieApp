package com.example.movieapp.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp.presentation.widget.TitleTopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    mainPadding: PaddingValues
) {
    Scaffold(
        modifier = Modifier.padding(bottom = mainPadding.calculateBottomPadding()),
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleTopBarText("Search") }
            )
        }
    ) { innerPadding ->

    }
}