package com.example.personlistviewmodel

import androidx.lifecycle.ViewModel
import com.example.person.GetPersonByFilter
import com.example.ui.uiState.PersonUIState
import com.example.utils.Constants
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PersonListViewModel @Inject constructor(
    private val getPersonByFilter: GetPersonByFilter
): ViewModel() {
    private var page = 1

    private val _personState = MutableStateFlow(PersonUIState.Loading as PersonUIState)
    val personState: StateFlow<PersonUIState> = _personState

    fun getPersons(queryParameters: List<Pair<String, String>>) = launchWithoutOld(GET_PERSONS_JOB) {
        if (personState.value is PersonUIState.Success) return@launchWithoutOld

        val res = getPersonByFilter.execute(queryParameters)

        res.onSuccess {
            _personState.value = PersonUIState.Success(it)
        }
    }

    fun loadMorePersons(queryParameters: List<Pair<String, String>>) = launchWithoutOld(LOAD_PERSONS_JOB) {
        page++

        val newQuery = queryParameters.toMutableList()
        newQuery.add(Constants.PAGE_FIELD to page.toString())

        val res = getPersonByFilter.execute(newQuery)

        res.onSuccess {
            val newRes = (personState.value as PersonUIState.Success)
                .data
                .toMutableList()
            newRes.addAll(it)

            _personState.value = PersonUIState.Success(newRes)
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_PERSONS_JOB = "get_persons"
        const val LOAD_PERSONS_JOB = "load_more_persons"
    }
}