package com.example.awardlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awards.GetMovieAwardsByDate
import com.example.awards.GetMovieAwardsByTitle
import com.example.awards.GetPersonAwardsByDate
import com.example.awards.GetPersonAwardsByTitle
import com.example.model.person.NominationAward
import com.example.ui.widget.bottomSheets.AwardsFilterType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AwardListViewModel @Inject constructor(
    private val getMovieAwardsByDate: GetMovieAwardsByDate,
    private val getMovieAwardsByTitle: GetMovieAwardsByTitle,
    private val getPersonAwardsByDate: GetPersonAwardsByDate,
    private val getPersonAwardsByTitle: GetPersonAwardsByTitle
): ViewModel() {
    private var page = 1
    private var awards by mutableStateOf<List<NominationAward>>(listOf())

    var currentFilterType by mutableStateOf(AwardsFilterType.BY_DATE)
        private set
    var visibleBottomSheet by mutableStateOf(false)
        private set

    var groupAwards by mutableStateOf<List<Pair<String, List<NominationAward>>>>(listOf())
        private set

    fun updateVisibleBottomSheet(state: Boolean) {
        visibleBottomSheet = state
    }

    fun updateCurrentFilter(state: AwardsFilterType) {
        currentFilterType = state
    }

    fun getAwards(id: Int, isMovie: Boolean) {
        page = 1
        groupAwards = listOf()

        when (currentFilterType) {
            AwardsFilterType.BY_TITLE -> getAwardsByTitle(id, isMovie)
            AwardsFilterType.BY_DATE -> getAwardsByDate(id, isMovie)
        }
    }

    fun loadMoreAwards(id: Int, isMovie: Boolean) {
        page++

        when (currentFilterType) {
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
                awards = it
                groupAwards = it.groupBy { award ->
                    award.nomination?.award?.title + ", " + award.nomination?.award?.year
                }.toList()
            }.onFailure {

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
                awards = data
                groupAwards = data.groupBy {
                    it.nomination?.award?.title + ", " + it.nomination?.award?.year
                }.toList()
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
                val temp = awards.toMutableList()
                temp.addAll(data)
                awards = temp

                groupAwards = temp.groupBy {
                    it.nomination?.award?.title + ", " + it.nomination?.award?.year
                }.toList()
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
                val temp = awards.toMutableList()
                temp.addAll(data)
                awards = temp

                groupAwards = temp.groupBy {
                    it.nomination?.award?.title + ", " + it.nomination?.award?.year
                }.toList()
            }
        }
    }
}