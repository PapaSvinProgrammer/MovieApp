package com.mordva.images_list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movieapp.images_list.R
import com.mordva.images_list.util.imageTypeDropDownItems
import com.mordva.images_list.util.toDropDownItem
import com.mordva.images_list.util.toImageType
import com.mordva.model.image.ImageType
import com.mordva.ui.widget.component.customDropDownList.SelectableMultiDropDownList
import com.mordva.ui.widget.other.TitleTopBarText
import dev.chrisbanes.haze.HazeState

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            SelectableMultiDropDownList(
                current = state.imageTypes.toDropDownItem(context),
                list = imageTypeDropDownItems(context),
                onClick = { item ->
                    viewModel.updateImageTypes(item.toImageType(context) ?: ImageType.ALL)
                }
            )
        }
    }
}