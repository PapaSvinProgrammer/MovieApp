package com.example.movieapp.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.repositories.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchSettingsViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {
    var selectedCategoryIndex by mutableIntStateOf(0)
        private set
    var selectedSortTypeIndex by mutableIntStateOf(0)
        private set

    val resultGenres = mutableStateListOf<Pair<String, Boolean>>()
    val resultCountries = mutableStateListOf<Pair<String, Boolean>>()

    var genreListVisible by mutableStateOf(false)
        private set
    var countryListVisible by mutableStateOf(false)
        private set

    var ratingSliderPosition by mutableStateOf(6f..10f)
        private set

    fun updateSelectedCategoryIndex(index: Int) {
        selectedCategoryIndex = index
    }

    fun updateSelectedSortTypeIndex(index: Int) {
        selectedSortTypeIndex = index
    }

    fun updateGenreVisible(state: Boolean) {
        genreListVisible = state
    }

    fun updateCountryVisible(state: Boolean) {
        countryListVisible = state
    }

    fun updateRatingSliderPosition(position: ClosedFloatingPointRange<Float>) {
        ratingSliderPosition = position
    }

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getGenres().map {
                val name = it.name ?: ""
                resultGenres.add(name to false)
            }
        }
    }

    fun getCounties() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getCounties().map {
                val name = it.name ?: ""
                resultCountries.add(name to false)
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
        selectedCategoryIndex = 0
        selectedSortTypeIndex = 0
        resetGenres()
        resetCountries()
        ratingSliderPosition = 6f..10f
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