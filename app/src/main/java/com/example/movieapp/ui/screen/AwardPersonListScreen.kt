package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.ui.widget.renderState.RenderAwardsContent
import com.example.movieapp.viewModels.AwardPersonListViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AwardPersonListScreen(
    navController: NavController,
    viewModel: AwardPersonListViewModel,
    hazeState: HazeState,
    id: Int
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(R.string.awards))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LifecycleEventEffect(Lifecycle.Event.ON_START) {
            viewModel.getAwards(id)
        }

        RenderAwardsContent(
            modifier = Modifier
                .padding(innerPadding)
                .haze(hazeState),
            list = viewModel.groupAwards,
            onClick = {},
            onLoadMore = { viewModel.loadMoreAwards(id) }
        )
    }
}