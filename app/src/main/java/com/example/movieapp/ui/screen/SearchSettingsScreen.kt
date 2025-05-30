package com.example.movieapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.ui.viewModel.SearchSettingsViewModel
import com.example.movieapp.ui.widget.component.TextListLayout
import com.example.movieapp.ui.widget.other.TitleTopBarText
import dev.chrisbanes.haze.HazeState
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSettingsScreen(
    navController: NavController,
    hazeState: HazeState,
    viewModel: SearchSettingsViewModel
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getGenres()
        viewModel.getCounties()
    }

    val optionsCategory = listOf(
        stringResource(R.string.all),
        stringResource(R.string.movies),
        stringResource(R.string.serials)
    )

    val optionsSortType = listOf(
        stringResource(R.string.by_rating_short),
        stringResource(R.string.by_popular_short),
        stringResource(R.string.by_date_short)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(R.string.search_settings))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    TextButton(onClick = { viewModel.resetAllSettings() }) {
                        Text(
                            text = stringResource(R.string.reset),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.height(15.dp))

                SegmentedButton(
                    selectedIndex = viewModel.selectedCategoryIndex,
                    list = optionsCategory,
                    onClick = { viewModel.updateSelectedCategoryIndex(it) }
                )

                DetailSettingsContent(
                    genresResult = viewModel.genresResult,
                    countriesResult = viewModel.countriesResult,
                    onCountryClick = { viewModel.updateCountryVisible(true) },
                    onGenreClick = { viewModel.updateGenreVisible(true) },
                    onYearClick = {  }
                )

                RatingRow(
                    sliderPosition = viewModel.ratingSliderPosition,
                    onChange = { viewModel.updateRatingSliderPosition(it) }
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.sorting_by),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(15.dp)
                )

                SegmentedButton(
                    selectedIndex = viewModel.selectedSortTypeIndex,
                    list = optionsSortType,
                    onClick = { viewModel.updateSelectedSortTypeIndex(it) }
                )
            }

            SuccessButton {

            }
        }
    }

    if (viewModel.genres.isNotEmpty()) {
        TextListLayout(
            visible = viewModel.genreListVisible,
            title = stringResource(R.string.genres),
            hazeState = hazeState,
            onClose = { viewModel.updateGenreVisible(false)},
            list = viewModel.genres,
            onResult = { viewModel.updateGenresResult(it) }
        )
    }

    if (viewModel.countries.isNotEmpty()) {
        TextListLayout(
            visible = viewModel.countryListVisible,
            title = stringResource(R.string.countries),
            hazeState = hazeState,
            onClose = { viewModel.updateCountryVisible(false) },
            list = viewModel.countries,
            onResult = { viewModel.updateCountriesResult(it)}
        )
    }
}

@Composable
private fun ColumnScope.SegmentedButton(
    selectedIndex: Int,
    list: List<String>,
    onClick: (Int) -> Unit
) {
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .align(Alignment.CenterHorizontally)
    ) {
        list.forEachIndexed { index, item ->
            SegmentedButton(
                selected = index == selectedIndex,
                label = { Text(item) },
                icon = {  },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = list.size,
                    baseShape = RoundedCornerShape(10.dp)
                ),
                onClick = { onClick(index) }
            )
        }
    }
}

@Composable
private fun DetailSettingsContent(
    genresResult: List<String>,
    countriesResult: List<String>,
    onGenreClick: () -> Unit,
    onCountryClick: () -> Unit,
    onYearClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 30.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
    ) {
        CategoryRow(
            index = 0,
            s = stringResource(R.string.genres),
            list = genresResult,
            defaultDescription = stringResource(R.string.any1),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )

        HorizontalDivider()

        CategoryRow(
            index = 1,
            s = stringResource(R.string.countries),
            list = countriesResult,
            defaultDescription = stringResource(R.string.any1),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )

        HorizontalDivider()

        CategoryRow(
            index = 2,
            s = stringResource(R.string.year),
            defaultDescription = stringResource(R.string.any2),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )
    }
}

@Composable
private fun CategoryRow(
    index: Int,
    s: String,
    list: List<String> = listOf(),
    defaultDescription: String = "",
    onGenreClick: () -> Unit,
    onCountryClick: () -> Unit,
    onYearClick: () -> Unit
) {
    val description = if (list.isEmpty())
        defaultDescription
    else
        list.joinToString(", ")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                when (index) {
                    0 -> onGenreClick()
                    1 -> onCountryClick()
                    2 -> onYearClick()
                }
            }
            .padding(15.dp)

    ) {
        Text(
            text = s,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            text = description,
            textAlign = TextAlign.End,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun BoxScope.SuccessButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(30.dp),
        onClick = onClick
    ) {
        Text(
            text = stringResource(R.string.show),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun RatingRow(
    sliderPosition: ClosedFloatingPointRange<Float>,
    onChange: (ClosedFloatingPointRange<Float>) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.rating),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = convertRating(sliderPosition),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    Spacer(modifier = Modifier.height(15.dp))

    RangeSlider(
        modifier = Modifier.padding(horizontal = 15.dp),
        value = sliderPosition,
        steps = 9,
        onValueChange = onChange,
        valueRange = 0f..10f,
        onValueChangeFinished = {}
    )
}

private fun convertRating(position: ClosedFloatingPointRange<Float>): String {
    val start = position.start.roundToInt()
    val end = position.endInclusive.roundToInt()

    val startStr = "от $start"
    val endStr = "до $end"

    if (start == end) {
        return "только $start"
    }

    if (start == 0 && end == 10) {
        return "неважно"
    }

    if (start == 0) {
        return endStr
    }

    if (end == 10) {
        return startStr
    }

    return startStr + endStr
}