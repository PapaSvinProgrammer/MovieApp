package com.example.movieapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.utils.ConvertData
import com.example.core.utils.FormatDate
import com.example.movieapp.R
import com.example.movieapp.app.navigation.MovieListRoute
import com.example.movieapp.ui.screen.uiState.PersonUIState
import com.example.movieapp.ui.widget.listItems.FactCard
import com.example.movieapp.ui.widget.listItems.TotalListItem
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.ui.widget.renderState.RenderFactStateRow
import com.example.movieapp.ui.widget.renderState.RenderMovieStateRow
import com.example.movieapp.viewModels.PersonViewModel
import com.example.network.module.person.Person
import com.example.network.utils.Constants

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonScreen(
    navController: NavController,
    viewModel: PersonViewModel,
    id: Int
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getPerson(id)
        viewModel.getMovies(id)
        viewModel.getCountAwards(id)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = "Person name")
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
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                RenderPersonContent(
                    state = viewModel.personState
                )
            }

            item {
                RenderMovieStateRow(
                    state = viewModel.moviesState,
                    title = stringResource(R.string.best_movies_and_serials),
                    onClick = {  },
                    onShowAll = {
                        val query = listOf(
                            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                            Constants.SORT_TYPE to Constants.SORT_DESC,
                            Constants.PERSONS_ID_FIELD to id.toString()
                        )

                        navController.navigate(
                            MovieListRoute(
                                queryParameters = query,
                                title = "Фильмы: ${
                                    (viewModel.personState as PersonUIState.Success)
                                        .data
                                        .first()
                                        .name
                                }"
                            )
                        )
                    }
                )
            }

            item {
                viewModel.countAwards?.let {
                    TotalListItem(
                        title = stringResource(R.string.awards),
                        value = it.toString(),
                        modifier = Modifier.clickable {

                        }
                    )
                }
            }

            item {
                RenderFactStateRow(
                    state = viewModel.factState,
                    title = "Знаете ли вы, что...",
                    onClick = {},
                    onShowAll = {}
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun RenderPersonContent(state: PersonUIState) {
    when (state) {
        PersonUIState.Loading -> {}
        is PersonUIState.Success ->  MainPersonContent(state.data.first())
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPersonContent(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(person.photo)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_movie),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(140.dp)
                .height(210.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(15.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
        ) {
            Column {
                NameContent(
                    name = person.name,
                    alternativeName = person.enName
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = FormatDate.formatDate(person.birthday ?: ""),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                AgeAndGrowthContent(
                    age = person.age,
                    growth = person.growth
                )
            }

            TextButton(
                modifier = Modifier.align(Alignment.BottomStart),
                contentPadding = PaddingValues(0.dp),
                onClick = {}
            ) {
                Text(text = stringResource(R.string.details))
            }
        }
    }
}

@Composable
private fun NameContent(
    name: String?,
    alternativeName: String?
) {
    Text(
        text = name ?: "",
        fontSize = 25.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 40.sp
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = alternativeName ?: "",
        fontSize = 14.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun AgeAndGrowthContent(
    age: Int?,
    growth: Int?
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        age?.let {
            val prettyAge = ConvertData.getPrettyAge(it)

            Text(
                text = prettyAge,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .size(3.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        shape = CircleShape
                    )
            )
        }

        growth?.let {
            val prettyGrowth = ConvertData.getPrettyGrowth(it)

            Text(
                text = prettyGrowth,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}