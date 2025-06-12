package com.example.movieapp.viewModels

import android.util.Log
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

class AwardPersonListViewModel @Inject constructor(
    private val getAward: GetAward
): ViewModel() {
    private var page = 1
    private var awards by mutableStateOf<List<NominationAwardPerson>>(listOf())

    var currentFilterType by mutableStateOf(AwardsFilterType.BY_DATE)
        private set
    var groupAwards by mutableStateOf<List<Pair<String, List<NominationAwardPerson>>>>(listOf())
        private set

    fun getAwards(id: Int) {
        page = 1

        when (currentFilterType) {
            AwardsFilterType.BY_TITLE -> getAwardsByTitle(id)
            AwardsFilterType.BY_DATE -> getAwardsByDate(id)
        }
    }

    fun loadMoreAwards(id: Int) {
        page++

        when (currentFilterType) {
            AwardsFilterType.BY_TITLE -> loadMoreByTitle(id, page)
            AwardsFilterType.BY_DATE -> loadMoreByDate(id, page)
        }
    }

    private fun getAwardsByTitle(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getAward.getPersonAwardsByTitle(id)

            if (res.docs.isNotEmpty()) {
                awards = res.docs
            }

            groupAwards = res.docs.groupBy {
                it.nomination?.award?.title + ", " + it.nomination?.award?.year
            }.toList()
        }
    }

    private fun getAwardsByDate(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getAward.getPersonAwardsByDate(id)

            if (res.docs.isNotEmpty()) {
                awards = res.docs
            }

            groupAwards = res.docs.groupBy {
                it.nomination?.award?.title + ", " + it.nomination?.award?.year
            }.toList()
        }
    }

    private fun loadMoreByTitle(id: Int, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getAward.getPersonAwardsByTitle(id, page)

            if (res.docs.isNotEmpty()) {
                val temp = awards.toMutableList()
                temp.addAll(res.docs)
                awards = temp

                Log.d("RRRR", temp.toString())

                groupAwards = temp.groupBy {
                    it.nomination?.award?.title + ", " + it.nomination?.award?.year
                }.toList()
            }
        }
    }

    private fun loadMoreByDate(id: Int, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getAward.getPersonAwardsByDate(id, page)

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