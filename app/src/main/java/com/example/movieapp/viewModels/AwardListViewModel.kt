package com.example.movieapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.module.AwardsFilterType
import com.example.core.domain.usecases.GetAward
import com.example.network.module.person.NominationAwardPerson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AwardListViewModel @Inject constructor(
    private val getAward: GetAward
): ViewModel() {
    private var page = 1
    private var awards by mutableStateOf<List<NominationAwardPerson>>(listOf())

    var currentFilterType by mutableStateOf(AwardsFilterType.BY_DATE)
        private set
    var visibleBottomSheet by mutableStateOf(false)
        private set

    var groupAwards by mutableStateOf<List<Pair<String, List<NominationAwardPerson>>>>(listOf())
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
                    getAward.getMovieAwardsByTitle(id)
                else
                    getAward.getPersonAwardsByTitle(id)

            if (res.docs.isNotEmpty()) {
                awards = res.docs
            }

            groupAwards = res.docs.groupBy {
                it.nomination?.award?.title + ", " + it.nomination?.award?.year
            }.toList()
        }
    }

    private fun getAwardsByDate(id: Int, isMovie: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = if (isMovie)
                getAward.getMovieAwardsByDate(id)
            else
                getAward.getPersonAwardsByDate(id)

            if (res.docs.isNotEmpty()) {
                awards = res.docs
            }

            groupAwards = res.docs.groupBy {
                it.nomination?.award?.title + ", " + it.nomination?.award?.year
            }.toList()
        }
    }

    private fun loadMoreByTitle(id: Int, page: Int, isMovie: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = if (isMovie)
                    getAward.getMovieAwardsByTitle(id, page)
                else
                    getAward.getPersonAwardsByTitle(id, page)

            if (res.docs.isNotEmpty()) {
                val temp = awards.toMutableList()
                temp.addAll(res.docs)
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
                getAward.getMovieAwardsByDate(id, page)
            else
                getAward.getPersonAwardsByDate(id, page)

            if (res.docs.isNotEmpty()) {
                val temp = awards.toMutableList()
                temp.addAll(res.docs)
                awards = temp

                groupAwards = temp.groupBy {
                    it.nomination?.award?.title + ", " + it.nomination?.award?.year
                }.toList()
            }
        }
    }
}