package com.example.settings.presentation.language

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movieapp.ui.R
import com.example.ui.widget.component.CustomSearchBar
import com.example.ui.widget.other.TitleTopBarText
import io.ktor.http.headers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LanguageScreen(
    navController: NavController,
    viewModel: LanguageViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.query) {
        viewModel.getLanguages()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(R.string.language))
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            CustomSearchBar(
                query = uiState.query,
                onValueChange = {
                    viewModel.updateQuery(it)
                }
            )

            LazyColumn {
                items(uiState.languages) {
                    ListItem(
                        headlineContent = {
                            Text(it.text)
                        }
                    )

                    HorizontalDivider()
                }
            }
        }
    }
}

