package com.mordva.awardlist.presentation

import androidx.lifecycle.ViewModel
import com.mordva.awardlist.domain.FilterAwardsByGroup
import com.mordva.awardlist.presentation.widget.UiState
import com.mordva.awardlist.presentation.widget.bottomSheet.AwardsFilterType
import com.mordva.awardlist.domain.GetAwards
import com.mordva.awardlist.domain.LoadMoreAwards
import com.mordva.awardlist.domain.model.RequestParams
import com.mordva.model.person.NominationAward
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class AwardListViewModel @Inject constructor(
    private val getAwards: GetAwards,
    private val loadMoreAwards: LoadMoreAwards,
    private val filterAwardsByGroup: FilterAwardsByGroup
) : ViewModel() {
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

    fun getAwards(id: Int, isMovie: Boolean) = launchWithoutOld(GET_AWARDS_JOB) {
        _uiState.update {
            it.copy(
                groupAwards = listOf(),
                page = 1
            )
        }

        val params = RequestParams(
            id = id,
            page = uiState.value.page,
            isMovie = isMovie,
            filterType = uiState.value.currentFilterType
        )

        getAwards.execute(params).onSuccess { awards ->
            _uiState.update {
                it.copy(groupAwards = filterAwardsByGroup.execute(awards))
            }
        }
    }

    fun loadMoreAwards(id: Int, isMovie: Boolean) = launchWithoutOld(GET_AWARDS_JOB) {
        _uiState.update {
            it.copy(page = it.page + 1)
        }

        val params = RequestParams(
            id = id,
            page = uiState.value.page,
            isMovie = isMovie,
            filterType = uiState.value.currentFilterType
        )

        loadMoreAwards.execute(params).onSuccess { data ->
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
        const val GET_AWARDS_JOB = "get_awards"
    }
}