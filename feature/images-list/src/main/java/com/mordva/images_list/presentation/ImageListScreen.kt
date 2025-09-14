package com.mordva.images_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.movieapp.images_list.R
import com.mordva.images_list.util.imageTypeDropDownItems
import com.mordva.images_list.util.toDropDownItem
import com.mordva.images_list.util.toImageType
import com.mordva.model.image.ImageType
import com.mordva.ui.theme.Typography
import com.mordva.ui.uiState.ImageUIState
import com.mordva.ui.widget.component.BasicLoadingBox
import com.mordva.ui.widget.component.customDropDownList.SelectableMultiDropDownList
import com.mordva.ui.widget.lazyComponent.EndlessLazyVerticalStaggeredGrid
import com.mordva.ui.widget.other.TitleTopBarText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ImageListScreen(
    navController: NavController,
    viewModel: ImageListViewModel,
    hazeState: HazeState,
    movieId: Int
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getImages(movieId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleTopBarText(stringResource(R.string.all_images)) },
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
                .padding(innerPadding)
                .hazeSource(hazeState)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Выберете, что хотите найти",
                    fontSize = Typography.bodyMedium.fontSize
                )

                SelectableMultiDropDownList(
                    current = state.imageTypes.toDropDownItem(context),
                    list = imageTypeDropDownItems(context),
                    onClick = { item ->
                        viewModel.updateImageTypes(item.toImageType(context) ?: ImageType.ALL)
                        viewModel.getImages(movieId)
                    }
                )
            }

            RenderMainContent(
                imageState = state.imagesState,
                onLoadMore = { viewModel.loadMoreImages(movieId) }
            )
        }
    }
}

@Composable
private fun RenderMainContent(
    imageState: ImageUIState,
    onLoadMore: () -> Unit
) {
    when (imageState) {
        ImageUIState.Loading -> BasicLoadingBox()
        is ImageUIState.Success -> {
            EndlessLazyVerticalStaggeredGrid(
                list = imageState.data,
                columns = StaggeredGridCells.Fixed(2),
                onLoadMore = onLoadMore,
            ) { poster ->
                AsyncImage(
                    model = poster.url,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}
