package com.mordva.search.presentation.searchSettings.widget

import com.mordva.search.presentation.widget.component.ListUIState
import com.mordva.util.Constants
import com.mordva.util.convert.FormatDate

internal data class SettingsUiState(
    val selectedCategoryIndex: Int = 0,
    val selectedSortTypeIndex: Int = 0,
    val resultGenres: MutableList<ListUIState> = mutableListOf(),
    val resultCountries: MutableList<ListUIState> = mutableListOf(),
    val yearFilter: Pair<Int, Int> = Constants.LAST_YEAR to FormatDate.getCurrentYear(),
    val ratingSliderPosition: ClosedFloatingPointRange<Float> = 6f..10f,
    val genreListVisible: Boolean = false,
    val countryListVisible: Boolean = false,
    val yearPickerVisible: Boolean = false
)