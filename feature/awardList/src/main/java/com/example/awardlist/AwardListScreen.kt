package com.example.awardlist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.awardlist.widget.bottomSheet.AwardsFilterSheet
import com.example.awardlist.widget.component.RenderAwardsContent
import com.example.movieapp.awardList.R
import com.example.ui.widget.other.TitleTopBarText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AwardListScreen(
    navController: NavController,
    viewModel: AwardListViewModel,
    hazeState: HazeState,
    id: Int,
    isMovie: Boolean
) {
    val currentFilterType by viewModel.currentFilterType.collectAsStateWithLifecycle()
    val visibleBottomSheet by viewModel.visibleBottomSheet.collectAsStateWithLifecycle()
    val groupAwards by viewModel.groupAwards.collectAsStateWithLifecycle()

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
            if (groupAwards.isEmpty()) {
                viewModel.getAwards(id, isMovie)
            }
        }

        RenderAwardsContent(
            modifier = Modifier
                .padding(innerPadding)
                .hazeSource(hazeState),
            list = groupAwards,
            onClick = {},
            onLoadMore = { viewModel.loadMoreAwards(id, isMovie) }
        )

        if (visibleBottomSheet) {
            AwardsFilterSheet(
                current = currentFilterType,
                onClick = {
                    viewModel.updateCurrentFilter(it)
                    viewModel.getAwards(id, isMovie)
                    viewModel.updateVisibleBottomSheet(false)
                },
                onDismissRequest = { viewModel.updateVisibleBottomSheet(false) }
            )
        }
    }
}