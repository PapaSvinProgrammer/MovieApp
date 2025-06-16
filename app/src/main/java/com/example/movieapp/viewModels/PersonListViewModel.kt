package com.example.movieapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetPerson
import com.example.movieapp.ui.uiState.PersonUIState
import com.example.network.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonListViewModel @Inject constructor(
    private val getPerson: GetPerson
): ViewModel() {
    private var page = 1
    var personState by mutableStateOf(PersonUIState.Loading as PersonUIState)
        private set

    fun getPersons(queryParameters: List<Pair<String, String>>) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.getPersonsByFilter(queryParameters)

            res.onSuccess {
                personState = PersonUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun loadMorePersons(queryParameters: List<Pair<String, String>>) {
        page++
        viewModelScope.launch(Dispatchers.IO) {
            val newQuery = queryParameters.toMutableList()
            newQuery.add(Constants.PAGE_FIELD to page.toString())

            val res = getPerson.getPersonsByFilter(newQuery)

            res.onSuccess {
                val newRes = (personState as PersonUIState.Success).data.toMutableList()
                newRes.addAll(it.docs)

                personState = PersonUIState.Success(newRes)
            }.onError {

            }
        }
    }
}