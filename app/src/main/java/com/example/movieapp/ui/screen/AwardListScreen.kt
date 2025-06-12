package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.ui.widget.bottomSheets.AwardsFilterSheet
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.ui.widget.renderState.RenderAwardsContent
import com.example.movieapp.viewModels.AwardListViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AwardListScreen(
    navController: NavController,
    viewModel: AwardListViewModel,
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
                    IconButton(onClick = { viewModel.updateVisibleBottomSheet(true) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_filter),
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

        if (viewModel.visibleBottomSheet) {
            AwardsFilterSheet(
                current = viewModel.currentFilterType,
                onClick = {
                    viewModel.updateCurrentFilter(it)
                    viewModel.getAwards(id)
                    viewModel.updateVisibleBottomSheet(false)
                },
                onDismissRequest = { viewModel.updateVisibleBottomSheet(false) }
            )
        }
    }
}