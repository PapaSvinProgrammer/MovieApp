package com.example.search.searchSettings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.movieapp.search.R
import com.example.navigationroute.SearchResultRoute
import com.example.search.searchScreen.widget.dialog.YearPickerDialog
import com.example.search.widget.component.DetailSettingsContent
import com.example.search.widget.component.SearchListLayout
import com.example.search.widget.row.RatingRow
import com.example.ui.widget.other.TitleTopBarText
import com.example.utils.ConvertData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSettingsScreen(
    navController: NavController,
    viewModel: SearchSettingsViewModel
) {
    val selectedCategoryIndex by viewModel.selectedCategoryIndex.collectAsStateWithLifecycle()
    val selectedSortTypeIndex by viewModel.selectedSortTypeIndex.collectAsStateWithLifecycle()
    val yearFilter by viewModel.yearFilter.collectAsStateWithLifecycle()
    val genreListVisible by viewModel.genreListVisible.collectAsStateWithLifecycle()
    val countryListVisible by viewModel.countryListVisible.collectAsStateWithLifecycle()
    val yearPickerVisible by viewModel.yearPickerVisible.collectAsStateWithLifecycle()
    val ratingSliderPosition by viewModel.ratingSliderPosition.collectAsStateWithLifecycle()
    val resultGenres by viewModel.resultGenres.collectAsStateWithLifecycle()
    val resultCountries by viewModel.resultCountries.collectAsStateWithLifecycle()

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
                    selectedIndex = selectedCategoryIndex,
                    list = optionsCategory,
                    onClick = { viewModel.updateSelectedCategoryIndex(it) }
                )

                DetailSettingsContent(
                    genresResult = resultGenres.filter { it.isChecked }.map { it.title },
                    countriesResult = resultCountries.filter { it.isChecked }.map { it.title },
                    yearResult = yearFilter,
                    onCountryClick = { viewModel.updateCountryVisible(true) },
                    onGenreClick = { viewModel.updateGenreVisible(true) },
                    onYearClick = { viewModel.updateVisibleYearPicker(true) }
                )

                RatingRow(
                    sliderPosition = ratingSliderPosition,
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
                    selectedIndex = selectedSortTypeIndex,
                    list = optionsSortType,
                    onClick = { viewModel.updateSelectedSortTypeIndex(it) }
                )
            }

            SuccessButton {
                val convertData = ConvertData.convertQueryParameters(
                    category = optionsCategory[selectedCategoryIndex],
                    sortBy = optionsSortType[selectedSortTypeIndex],
                    genres = resultGenres.filter { it.isChecked }.map { it.title },
                    counties = resultCountries.filter { it.isChecked }.map { it.title },
                    rating = ratingSliderPosition,
                    year = yearFilter
                )

                navController.navigate(SearchResultRoute(convertData)) {
                    launchSingleTop = true
                }
            }
        }
    }

    SearchListLayout(
        visible = genreListVisible,
        title = stringResource(R.string.genres),
        onClose = { viewModel.updateGenreVisible(false)},
        list = resultGenres,
        onClick = { viewModel.updateResultGenres(it) },
        onReset = { viewModel.resetGenres() }
    )

    SearchListLayout(
        visible = countryListVisible,
        title = stringResource(R.string.countries),
        onClose = { viewModel.updateCountryVisible(false) },
        list = resultCountries,
        onClick = { viewModel.updateResultCountries(it) },
        onReset = { viewModel.resetCountries() }
    )

    if (yearPickerVisible) {
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