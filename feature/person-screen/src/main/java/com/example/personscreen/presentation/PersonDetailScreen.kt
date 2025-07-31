package com.example.personscreen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.navigationroute.PersonRoute
import com.example.personscreen.presentation.widget.content.SpouseContent
import com.example.personscreen.presentation.widget.render.RenderPersonDetailState
import com.example.ui.uiState.PersonUIState
import com.example.ui.widget.other.TitleTopBarText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PersonDetailScreen(
    navController: NavController,
    viewModel: PersonViewModel,
    id: Int
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getPerson(id)
    }

    LaunchedEffect(uiState.personState) {
        (uiState.personState as? PersonUIState.Success)?.data?.let { persons ->
            viewModel.getSpouses(persons.first().spouses.map { it.id })
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(
                        text = (uiState.personState as? PersonUIState.Success)
                            ?.data
                            ?.first()
                            ?.name ?: ""
                    )
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
        Column(modifier = Modifier.padding(innerPadding)) {
            RenderPersonDetailState(uiState.personState)

            (uiState.personState as? PersonUIState.Success)?.data?.let { data ->
                if (data.first().spouses.isEmpty()) return@Scaffold

                SpouseContent(
                    state = uiState.personSpouseState,
                    spouses = data.first().spouses,
                    onClick = {
                        navController.navigate(PersonRoute(it)) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}