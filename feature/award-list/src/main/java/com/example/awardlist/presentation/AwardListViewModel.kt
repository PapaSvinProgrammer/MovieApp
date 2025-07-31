package com.example.awardlist.presentation

import androidx.lifecycle.ViewModel
import com.example.awardlist.domain.FilterAwardsByGroup
import com.example.awardlist.presentation.widget.UiState
import com.example.awardlist.presentation.widget.bottomSheet.AwardsFilterType
import com.example.awards.GetMovieAwardsByDate
import com.example.awards.GetMovieAwardsByTitle
import com.example.awards.GetPersonAwardsByDate
import com.example.awards.GetPersonAwardsByTitle
import com.example.awards.model.AwardParams
import com.example.model.person.NominationAward
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class AwardListViewModel @Inject constructor(
    private val getMovieAwardsByDate: GetMovieAwardsByDate,
    private val getMovieAwardsByTitle: GetMovieAwardsByTitle,
    private val getPersonAwardsByDate: GetPersonAwardsByDate,
    private val getPersonAwardsByTitle: GetPersonAwardsByTitle,
    private val filterAwardsByGroup: FilterAwardsByGroup
) : ViewModel() {
    private var page = 1
    private val _awards = MutableStateFlow<List<NominationAward>>(listOf())
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun updateVisibleBottomSheet(state: Boolean) {
        _uiState.update {
            it.copy(visibleBottomSheet = state)
        }
    }

    fun updateCurrentFilter(state: AwardsFilterType) {
        _uiState.update {
            it.copy(currentFilterType = state)
        }
    }

    fun getAwards(id: Int, isMovie: Boolean) {
        page = 1
        _uiState.update {
            it.copy(groupAwards = listOf())
        }

        when (uiState.value.currentFilterType) {
            AwardsFilterType.BY_TITLE -> getAwardsByTitle(id, isMovie)
            AwardsFilterType.BY_DATE -> getAwardsByDate(id, isMovie)
        }
    }

    fun loadMoreAwards(id: Int, isMovie: Boolean) {
        page++

        when (uiState.value.currentFilterType) {
            AwardsFilterType.BY_TITLE -> loadMoreByTitle(id, page, isMovie)
            AwardsFilterType.BY_DATE -> loadMoreByDate(id, page, isMovie)
        }
    }

    private fun getAwardsByTitle(id: Int, isMovie: Boolean) = launchWithoutOld(GET_BY_TITLE_JOB) {
        val params = AwardParams(id = id)

        val res = if (isMovie)
            getMovieAwardsByTitle.execute(params)
        else
            getPersonAwardsByTitle.execute(params)

        res.onSuccess { awards ->
            _awards.value = awards

            _uiState.update {
                it.copy(groupAwards = filterAwardsByGroup.execute(awards))
            }
        }
    }

    private fun getAwardsByDate(id: Int, isMovie: Boolean) = launchWithoutOld(GET_BY_DATE_JOB) {
        val params = AwardParams(id = id)

        val res = if (isMovie)
            getMovieAwardsByDate.execute(params)
        else
            getPersonAwardsByDate.execute(params)

        res.onSuccess { awards ->
            _awards.value = awards

            _uiState.update {
                it.copy(groupAwards = filterAwardsByGroup.execute(awards))
            }
        }
    }

    private fun loadMoreByTitle(
        id: Int,
        page: Int,
        isMovie: Boolean
    ) = launchWithoutOld(MORE_BY_TITLE_JOB) {
        val params = AwardParams(
            id = id,
            page = page
        )

        val res = if (isMovie)
            getMovieAwardsByTitle.execute(params)
        else
            getPersonAwardsByTitle.execute(params)

        res.onSuccess { data ->
            val temp = _awards.value.toMutableList()
            temp.addAll(data)

            _awards.value = temp
            _uiState.update {
                it.copy(groupAwards = filterAwardsByGroup.execute(data))
            }
        }
    }

    private fun loadMoreByDate(
        id: Int,
        page: Int,
        isMovie: Boolean
    ) = launchWithoutOld(MORE_BY_DATE_JOB) {
        val params = AwardParams(
            id = id,
            page = page
        )

        val res = if (isMovie)
            getMovieAwardsByDate.execute(params)
        else
            getPersonAwardsByDate.execute(params)

        res.onSuccess { data ->
            val temp = _awards.value.toMutableList()
            temp.addAll(data)
            _awards.value = temp

            _uiState.update {
                it.copy(groupAwards = filterAwardsByGroup.execute(data))
            }
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val MORE_BY_DATE_JOB = "load_more_awards_by_date"
        const val MORE_BY_TITLE_JOB = "load_more_awards_by_title"
        const val GET_BY_DATE_JOB = "get_awards_by_date"
        const val GET_BY_TITLE_JOB = "get_awards_by_title"
    }
}