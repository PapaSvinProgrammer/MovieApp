package com.example.personlistviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Constants
import com.example.person.GetPersonByFilter
import com.example.ui.uiState.PersonUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonListViewModel @Inject constructor(
    private val getPersonByFilter: GetPersonByFilter
): ViewModel() {
    private var page = 1
    var personState by mutableStateOf(PersonUIState.Loading as PersonUIState)
        private set

    fun getPersons(queryParameters: List<Pair<String, String>>) {
        if (personState is PersonUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPersonByFilter.execute(queryParameters)

            res.onSuccess {
                personState = PersonUIState.Success(it)
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
                val newRes = (personState as PersonUIState.Success).data.toMutableList()
                newRes.addAll(it)

                personState = PersonUIState.Success(newRes)
            }
        }
    }
}