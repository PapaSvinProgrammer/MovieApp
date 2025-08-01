package com.example.movieScreen.presentation.groupPerson

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieScreen.domain.model.PersonMovieExtended
import com.example.movieScreen.presentation.groupPerson.widget.GroupUiState
import com.example.movieapp.ui.R
import com.example.navigationroute.model.PersonMovieScreenObject
import com.example.ui.widget.component.BasicLoadingBox
import com.example.ui.widget.other.TitleTopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GroupPersonsScreen(
    navController: NavController,
    list: List<PersonMovieScreenObject>,
    viewModel: GroupPersonViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getGroupedPersons(list)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(R.string.persons))
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
        when (val state = uiState) {
            GroupUiState.Loading -> BasicLoadingBox()
            is GroupUiState.Success -> {
                MainContent(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    map = state.data,
                    onClick = {  }
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    map: Map<String, List<PersonMovieExtended>>,
    onClick: () -> Unit
) {
    Log.d("RRRR", map.toString())
}