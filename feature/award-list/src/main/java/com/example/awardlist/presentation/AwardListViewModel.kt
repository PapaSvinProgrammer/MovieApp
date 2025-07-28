package com.example.awardlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awardlist.domain.FilterAwardsByGroup
import com.example.awardlist.presentation.widget.bottomSheet.AwardsFilterType
import com.example.awards.GetMovieAwardsByDate
import com.example.awards.GetMovieAwardsByTitle
import com.example.awards.GetPersonAwardsByDate
import com.example.awards.GetPersonAwardsByTitle
import com.example.model.person.NominationAward
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AwardListViewModel @Inject constructor(
    private val getMovieAwardsByDate: GetMovieAwardsByDate,
    private val getMovieAwardsByTitle: GetMovieAwardsByTitle,
    private val getPersonAwardsByDate: GetPersonAwardsByDate,
    private val getPersonAwardsByTitle: GetPersonAwardsByTitle,
    private val filterAwardsByGroup: FilterAwardsByGroup
): ViewModel() {
    private var page = 1
    private val _awards = MutableStateFlow<List<NominationAward>>(listOf())

    private val _currentFilterType = MutableStateFlow(AwardsFilterType.BY_DATE)
    private val _groupAwards = MutableStateFlow<List<Pair<String, List<NominationAward>>>>(listOf())
    private val _visibleBottomSheet = MutableStateFlow(false)

    val currentFilterType: StateFlow<AwardsFilterType> = _currentFilterType
    val visibleBottomSheet: StateFlow<Boolean> = _visibleBottomSheet
    val groupAwards: StateFlow<List<Pair<String, List<NominationAward>>>> = _groupAwards

    fun updateVisibleBottomSheet(state: Boolean) {
        _visibleBottomSheet.value = state
    }

    fun updateCurrentFilter(state: AwardsFilterType) {
        _currentFilterType.value = state
    }

    fun getAwards(id: Int, isMovie: Boolean) {
        page = 1
        _groupAwards.value = listOf()

        when (currentFilterType.value) {
            AwardsFilterType.BY_TITLE -> getAwardsByTitle(id, isMovie)
            AwardsFilterType.BY_DATE -> getAwardsByDate(id, isMovie)
        }
    }

    fun loadMoreAwards(id: Int, isMovie: Boolean) {
        page++

        when (currentFilterType.value) {
            AwardsFilterType.BY_TITLE -> loadMoreByTitle(id, page, isMovie)
            AwardsFilterType.BY_DATE -> loadMoreByDate(id, page, isMovie)
        }
    }

    private fun getAwardsByTitle(id: Int, isMovie: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = if (isMovie)
                    getMovieAwardsByTitle.execute(id)
                else
                    getPersonAwardsByTitle.execute(id)

            res.onSuccess {
                _awards.value = it
                _groupAwards.value = filterAwardsByGroup.execute(it)
            }
        }
    }

    private fun getAwardsByDate(id: Int, isMovie: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = if (isMovie)
                getMovieAwardsByDate.execute(id)
            else
                getPersonAwardsByDate.execute(id)

            res.onSuccess { data ->
                _awards.value = data
                _groupAwards.value = filterAwardsByGroup.execute(data)
            }
        }
    }

    private fun loadMoreByTitle(id: Int, page: Int, isMovie: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = if (isMovie)
                    getMovieAwardsByTitle.execute(id, page)
                else
                    getPersonAwardsByTitle.execute(id, page)

            res.onSuccess { data ->
                val temp = _awards.value.toMutableList()
                temp.addAll(data)

                _awards.value= temp
                _groupAwards.value = filterAwardsByGroup.execute(temp)
            }
        }
    }

    private fun loadMoreByDate(id: Int, page: Int, isMovie: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = if (isMovie)
                getMovieAwardsByDate.execute(id, page)
            else
                getPersonAwardsByDate.execute(id, page)

            res.onSuccess { data ->
                val temp = _awards.value.toMutableList()
                temp.addAll(data)
                _awards.value = temp

                _groupAwards.value = filterAwardsByGroup.execute(temp)
            }
        }
    }
}