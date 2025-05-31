package com.example.movieapp.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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

    var genreListVisible by mutableStateOf(false)
        private set
    var countryListVisible by mutableStateOf(false)
        private set

    var genresResult by mutableStateOf<List<String>>(listOf())
        private set
    var countriesResult by mutableStateOf<List<String>>(listOf())
        private set

    var genres by mutableStateOf<List<String>>(listOf())
        private set
    var countries by mutableStateOf<List<String>>(listOf())
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

    fun updateGenresResult(list: List<String>) {
        genresResult = list
    }

    fun updateCountriesResult(list: List<String>) {
        countriesResult = list
    }

    fun updateRatingSliderPosition(position: ClosedFloatingPointRange<Float>) {
        ratingSliderPosition = position
    }

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            genres = categoryRepository.getGenres().map { it.name ?: "" }
        }
    }

    fun getCounties() {
        viewModelScope.launch(Dispatchers.IO) {
            countries = categoryRepository.getCounties().map { it.name ?: "" }
        }
    }

    fun resetAllSettings() {
        selectedCategoryIndex = 0
        selectedSortTypeIndex = 0
        genresResult = listOf()
        countriesResult = listOf()
        ratingSliderPosition = 6f..10f
    }
}