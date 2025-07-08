package com.example.personlistviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Constants
import com.example.person.GetPersonByFilter
import com.example.ui.uiState.PersonUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonListViewModel @Inject constructor(
    private val getPersonByFilter: GetPersonByFilter
): ViewModel() {
    private var page = 1

    private val _personState = MutableStateFlow(PersonUIState.Loading as PersonUIState)
    val personState: StateFlow<PersonUIState> = _personState

    fun getPersons(queryParameters: List<Pair<String, String>>) {
        if (personState.value is PersonUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPersonByFilter.execute(queryParameters)

            res.onSuccess {
                _personState.value = PersonUIState.Success(it)
            }
        }
    }

    fun loadMorePersons(queryParameters: List<Pair<String, String>>) {
        page++
        viewModelScope.launch(Dispatchers.IO) {
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
    }
}