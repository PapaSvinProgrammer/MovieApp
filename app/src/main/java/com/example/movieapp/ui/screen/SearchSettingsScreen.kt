package com.example.movieapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import com.example.core.utils.ConvertData
import com.example.movieapp.R
import com.example.movieapp.app.navigation.SearchResultRoute
import com.example.movieapp.ui.widget.component.TextListLayout
import com.example.movieapp.ui.widget.dialog.YearPickerDialog
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.viewModels.SearchSettingsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSettingsScreen(
    navController: NavController,
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
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
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
                    genresResult = viewModel.resultGenres.filter { it.second }.map { it.first },
                    countriesResult = viewModel.resultCountries.filter { it.second }.map { it.first },
                    yearResult = viewModel.yearFilter,
                    onCountryClick = { viewModel.updateCountryVisible(true) },
                    onGenreClick = { viewModel.updateGenreVisible(true) },
                    onYearClick = { viewModel.updateVisibleYearPicker(true) }
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
                val convertData = ConvertData.convertQueryParameters(
                    category = optionsCategory[viewModel.selectedCategoryIndex],
                    sortBy = optionsSortType[viewModel.selectedSortTypeIndex],
                    genres = viewModel.resultGenres.filter { it.second }.map { it.first },
                    counties = viewModel.resultCountries.filter { it.second }.map { it.first },
                    rating = viewModel.ratingSliderPosition,
                    year = viewModel.yearFilter
                )

                navController.navigate(SearchResultRoute(convertData))
            }
        }
    }

    TextListLayout(
        visible = viewModel.genreListVisible,
        title = stringResource(R.string.genres),
        onClose = { viewModel.updateGenreVisible(false)},
        list = viewModel.resultGenres,
        onClick = { viewModel.updateResultGenres(it) },
        onReset = { viewModel.resetGenres() }
    )

    TextListLayout(
        visible = viewModel.countryListVisible,
        title = stringResource(R.string.countries),
        onClose = { viewModel.updateCountryVisible(false) },
        list = viewModel.resultCountries,
        onClick = { viewModel.updateResultCountries(it) },
        onReset = { viewModel.resetCountries() }
    )

    if (viewModel.yearPickerVisible) {
        YearPickerDialog(
            onDismiss = { viewModel.updateVisibleYearPicker(false) },
            onConfirm = { start, end ->
                viewModel.updateYearFilter(start, end)
                viewModel.updateVisibleYearPicker(false)
            }
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
                label = {
                    Text(
                        text = item,
                        maxLines = 1
                    )
                },
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DetailSettingsContent(
    genresResult: List<String>,
    countriesResult: List<String>,
    yearResult: Pair<Int, Int>,
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
            defaultDescription = ConvertData.convertYearRange(yearResult.first, yearResult.second),
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
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            text = description,
            textAlign = TextAlign.End,
            fontSize = 13.sp,
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
            text = ConvertData.convertRatingRange(sliderPosition),
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