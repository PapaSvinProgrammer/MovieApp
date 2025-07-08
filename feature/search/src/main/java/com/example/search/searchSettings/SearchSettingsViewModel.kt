package com.example.search.searchSettings

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Constants
import com.example.data.external.CategoryRepository
import com.example.utils.FormatDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchSettingsViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {
    private val _selectedCategoryIndex = MutableStateFlow(0)
    private val _selectedSortTypeIndex = MutableStateFlow(0)
    val selectedCategoryIndex: StateFlow<Int> = _selectedCategoryIndex
    val selectedSortTypeIndex: StateFlow<Int> = _selectedSortTypeIndex

    val resultGenres = mutableStateListOf<Pair<String, Boolean>>()
    val resultCountries = mutableStateListOf<Pair<String, Boolean>>()

    private val _yearFilter = MutableStateFlow(Constants.LAST_YEAR to FormatDate.getCurrentYear())
    var yearFilter: MutableStateFlow<Pair<Int, Int>> = _yearFilter

    private val _genreListVisible = MutableStateFlow(false)
    private val _countryListVisible = MutableStateFlow(false)
    private val _yearPickerVisible = MutableStateFlow(false)
    val genreListVisible: StateFlow<Boolean> = _genreListVisible
    val countryListVisible: StateFlow<Boolean> = _countryListVisible
    val yearPickerVisible: StateFlow<Boolean> = _yearPickerVisible

    private val _ratingSliderPosition = MutableStateFlow(6f..10f)
    var ratingSliderPosition: StateFlow<ClosedFloatingPointRange<Float>> = _ratingSliderPosition

    fun updateYearFilter(start: Int, end: Int) {
        _yearFilter.value = start to end
    }

    fun updateVisibleYearPicker(state: Boolean) {
        _yearPickerVisible.value = state
    }

    fun updateSelectedCategoryIndex(index: Int) {
        _selectedCategoryIndex.value = index
    }

    fun updateSelectedSortTypeIndex(index: Int) {
        _selectedSortTypeIndex.value = index
    }

    fun updateGenreVisible(state: Boolean) {
        _genreListVisible.value = state
    }

    fun updateCountryVisible(state: Boolean) {
        _countryListVisible.value = state
    }

    fun updateRatingSliderPosition(position: ClosedFloatingPointRange<Float>) {
        _ratingSliderPosition.value = position
    }

    fun getGenres() {
        if (resultGenres.isNotEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getGenres()
                .onSuccess { data ->
                    data.map {
                        val name = it.name
                        resultGenres.add(name to false)
                    }
                }
        }
    }

    fun getCounties() {
        if (resultCountries.isNotEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getCounties()
                .onSuccess { data ->
                    data.map {
                        val name = it.name
                        resultCountries.add(name to false)
                    }
                }
        }
    }

    fun updateResultGenres(index: Int) {
        resultGenres[index] = resultGenres[index].copy(
            second = !resultGenres[index].second
        )
    }

    fun updateResultCountries(index: Int) {
        resultCountries[index] = resultCountries[index].copy(
            second = !resultCountries[index].second
        )
    }

    fun resetAllSettings() {
        _selectedCategoryIndex.value = 0
        _selectedSortTypeIndex.value = 0
        resetGenres()
        resetCountries()
        _ratingSliderPosition.value = 6f..10f
        _yearFilter.value = Constants.LAST_YEAR to FormatDate.getCurrentYear()
    }

    fun resetGenres() {
        repeat(resultGenres.size) { index ->
            resultGenres[index] = resultGenres[index].copy(second = false)
        }
    }

    fun resetCountries() {
        repeat(resultCountries.size) { index ->
            resultCountries[index] = resultCountries[index].copy(second = false)
        }
    }
}