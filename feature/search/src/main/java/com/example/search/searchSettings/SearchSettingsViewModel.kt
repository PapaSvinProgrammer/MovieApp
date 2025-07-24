package com.example.search.searchSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.Constants
import com.example.data.external.CategoryRepository
import com.example.search.widget.component.ListUIState
import com.example.utils.convert.FormatDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SearchSettingsViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _selectedCategoryIndex = MutableStateFlow(0)
    private val _selectedSortTypeIndex = MutableStateFlow(0)
    val selectedCategoryIndex: StateFlow<Int> = _selectedCategoryIndex
    val selectedSortTypeIndex: StateFlow<Int> = _selectedSortTypeIndex

    private val _resultGenres = MutableStateFlow<MutableList<ListUIState>>(mutableListOf())
    private val _resultCountries = MutableStateFlow<MutableList<ListUIState>>(mutableListOf())
    val resultGenres: StateFlow<List<ListUIState>> = _resultGenres
    val resultCountries: StateFlow<List<ListUIState>> = _resultCountries

    private val _yearFilter = MutableStateFlow(Constants.LAST_YEAR to FormatDate.getCurrentYear())
    val yearFilter: MutableStateFlow<Pair<Int, Int>> = _yearFilter

    private val _genreListVisible = MutableStateFlow(false)
    private val _countryListVisible = MutableStateFlow(false)
    private val _yearPickerVisible = MutableStateFlow(false)
    val genreListVisible: StateFlow<Boolean> = _genreListVisible
    val countryListVisible: StateFlow<Boolean> = _countryListVisible
    val yearPickerVisible: StateFlow<Boolean> = _yearPickerVisible

    private val _ratingSliderPosition = MutableStateFlow(6f..10f)
    val ratingSliderPosition: StateFlow<ClosedFloatingPointRange<Float>> = _ratingSliderPosition

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
        if (resultGenres.value.isNotEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getGenres().onSuccess { data ->
                val list = data.map {
                    ListUIState(
                        title = it.name,
                        isChecked = false
                    )
                }.toMutableList()

                _resultGenres.value = list
            }
        }
    }

    fun getCounties() {
        if (resultCountries.value.isNotEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getCounties().onSuccess { data ->
                val list = data.map {
                    ListUIState(
                        title = it.name,
                        isChecked = false
                    )
                }.toMutableList()

                _resultCountries.value = list
            }
        }
    }

    fun updateResultGenres(index: Int) {
        val data = resultGenres.value[index]

        _resultGenres.value[index] = data.copy(
            isChecked = !data.isChecked
        )
    }

    fun updateResultCountries(index: Int) {
        val data = resultCountries.value[index]

        _resultCountries.value[index] = data.copy(
            isChecked = !data.isChecked
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
        repeat(resultGenres.value.size) { index ->
            val data = resultGenres.value[index]

            _resultGenres.value[index] = data.copy(
                isChecked = false
            )
        }
    }

    fun resetCountries() {
        repeat(resultCountries.value.size) { index ->
            val data = resultCountries.value[index]
            _resultCountries.value[index] = data.copy(
                isChecked = false
            )
        }
    }
}