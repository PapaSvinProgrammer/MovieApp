package com.mordva.movieScreen.presentation.groupPerson

import androidx.lifecycle.ViewModel
import com.mordva.movieScreen.domain.GetPersonLittleById
import com.mordva.movieScreen.domain.GroupPersonsByProfession
import com.mordva.movieScreen.domain.UnionPersonsAndPersonMovie
import com.mordva.movieScreen.domain.model.PersonMovieScreenObject
import com.mordva.movieScreen.domain.model.UnionParams
import com.mordva.movieScreen.presentation.groupPerson.widget.GroupUiState
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import com.mordva.util.multiRequest
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
