package com.example.search.searchSettings

import androidx.lifecycle.ViewModel
import com.example.data.external.CategoryRepository
import com.example.search.searchSettings.widget.SettingsUiState
import com.example.search.widget.component.ListUIState
import com.example.utils.Constants
import com.example.utils.cancelAllJobs
import com.example.utils.convert.FormatDate
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class SearchSettingsViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    fun updateYearFilter(start: Int, end: Int) {
        _uiState.update {
            it.copy(yearFilter = start to end)
        }
    }

    fun updateVisibleYearPicker(state: Boolean) {
        _uiState.update {
            it.copy(yearPickerVisible = state)
        }
    }

    fun updateSelectedCategoryIndex(index: Int) {
        _uiState.update {
            it.copy(selectedCategoryIndex = index)
        }
    }

    fun updateSelectedSortTypeIndex(index: Int) {
        _uiState.update {
            it.copy(selectedSortTypeIndex = index)
        }
    }

    fun updateGenreVisible(state: Boolean) {
        _uiState.update {
            it.copy(genreListVisible = state)
        }
    }

    fun updateCountryVisible(state: Boolean) {
        _uiState.update {
            it.copy(countryListVisible = state)
        }
    }

    fun updateRatingSliderPosition(position: ClosedFloatingPointRange<Float>) {
        _uiState.update {
            it.copy(ratingSliderPosition = position)
        }
    }

    fun getGenres() = launchWithoutOld(GET_GENRES_JOB) {
        if (uiState.value.resultGenres.isNotEmpty()) return@launchWithoutOld

        categoryRepository.getGenres().onSuccess { data ->
            val list = data.map {
                ListUIState(
                    title = it.name,
                    isChecked = false
                )
            }.toMutableList()

            _uiState.update {
                it.copy(resultGenres = list)
            }
        }
    }

    fun getCounties() = launchWithoutOld(GET_COUNTRIES_JOB) {
        if (uiState.value.resultCountries.isNotEmpty()) return@launchWithoutOld

        categoryRepository.getCounties().onSuccess { data ->
            val list = data.map {
                ListUIState(
                    title = it.name,
                    isChecked = false
                )
            }.toMutableList()

            _uiState.update {
                it.copy(resultCountries = list)
            }
        }
    }

    fun updateResultGenres(index: Int) {
        val data = uiState.value.resultGenres[index]

        _uiState.value.resultGenres[index] = data.copy(
            isChecked = !data.isChecked
        )
    }

    fun updateResultCountries(index: Int) {
        val data = uiState.value.resultCountries[index]

        _uiState.value.resultCountries[index] = data.copy(
            isChecked = !data.isChecked
        )
    }

    fun resetAllSettings() {
        _uiState.update { it.copy(selectedCategoryIndex = 0) }
        _uiState.update { it.copy(selectedSortTypeIndex = 0) }
        resetGenres()
        resetCountries()
        _uiState.update { it.copy(ratingSliderPosition = 6f..10f) }
        _uiState.update { it.copy(yearFilter = Constants.LAST_YEAR to FormatDate.getCurrentYear()) }
    }

    fun resetGenres() {
        repeat(uiState.value.resultGenres.size) { index ->
            val data = uiState.value.resultGenres[index]

            uiState.value.resultGenres[index] = data.copy(
                isChecked = false
            )
        }
    }

    fun resetCountries() {
        repeat(uiState.value.resultCountries.size) { index ->
            val data = uiState.value.resultCountries[index]

            uiState.value.resultCountries[index] = data.copy(
                isChecked = false
            )
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_GENRES_JOB = "get_genres"
        const val GET_COUNTRIES_JOB = "get_countries"
    }
}