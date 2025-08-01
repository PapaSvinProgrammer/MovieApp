package com.example.movieScreen.presentation.groupPerson

import androidx.lifecycle.ViewModel
import com.example.movieScreen.domain.GetPersonLittleById
import com.example.movieScreen.domain.GroupPersonsByProfession
import com.example.movieScreen.domain.UnionPersonsAndPersonMovie
import com.example.movieScreen.domain.model.UnionParams
import com.example.movieScreen.presentation.groupPerson.widget.GroupUiState
import com.example.navigationroute.model.PersonMovieScreenObject
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import com.example.utils.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class GroupPersonViewModel @Inject constructor(
    private val getPersonByFilter: GetPersonLittleById,
    private val groupPersonsByProfession: GroupPersonsByProfession,
    private val unionPersonsAndPersonMovie: UnionPersonsAndPersonMovie
) : ViewModel() {
    private val _uiState = MutableStateFlow(GroupUiState.Loading as GroupUiState)
    val uiState = _uiState.asStateFlow()

    fun getGroupedPersons(persons: List<PersonMovieScreenObject>) = launchWithoutOld(GET_PERSONS) {
        val responsePersons = multiRequest(persons) { person ->
            getPersonByFilter.execute(person.id)
        }

        val unionParams = UnionParams(
            personsList = responsePersons,
            personsScreenObjectList = persons
        )

        val mergedList = unionPersonsAndPersonMovie.execute(unionParams)
        val groupedPersons = groupPersonsByProfession.execute(mergedList)

        _uiState.value = GroupUiState.Success(groupedPersons)
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_PERSONS = "get_persons"
    }
}
