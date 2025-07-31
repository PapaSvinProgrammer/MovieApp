package com.example.personscreen.presentation

import androidx.lifecycle.ViewModel
import com.example.awards.GetPersonAwardsByDate
import com.example.awards.model.AwardParams
import com.example.model.movie.ShortMovie
import com.example.movieScreen.GetMovieByFilter
import com.example.person.GetPersonById
import com.example.personscreen.domain.GroupShortMovie
import com.example.personscreen.presentation.widget.UiState
import com.example.ui.uiState.FactUIState
import com.example.ui.uiState.MovieUIState
import com.example.ui.uiState.PersonUIState
import com.example.utils.Constants
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import com.example.utils.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class PersonViewModel @Inject constructor(
    private val getPersonById: GetPersonById,
    private val getMovieByFilter: GetMovieByFilter,
    private val getPersonAwardsByDate: GetPersonAwardsByDate,
    private val groupShortMovie: GroupShortMovie
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun updateSelectedGroup(index: Int) {
        _uiState.update {
            it.copy(selectedGroup = index)
        }

        if (uiState.value.groups.isNotEmpty()) {
            val currentKey = uiState.value.groupsKeys[index]

            _uiState.update {
                it.copy(moviesFromGroup = uiState.value.groups[currentKey] ?: listOf())
            }
        }
    }

    fun getPerson(id: Int) = launchWithoutOld(GET_PERSON_JOB) {
        if (uiState.value.personState is PersonUIState.Success) return@launchWithoutOld

        val res = getPersonById.execute(id)

        res.onSuccess { person ->
            _uiState.update {
                it.copy(personState = PersonUIState.Success(listOf(person)))
            }

            _uiState.update {
                it.copy(factState = FactUIState.Success(person.facts))
            }

            getGroups(person.movies)
        }
    }

    fun getMovies(personId: Int) = launchWithoutOld(GET_MOVIES_JOB) {
        if (uiState.value.moviesState is MovieUIState.Success) return@launchWithoutOld

        val query = listOf(
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC,
            Constants.PERSONS_ID_FIELD to personId.toString()
        )

        val res = getMovieByFilter.execute(query)

        res.onSuccess { movies ->

            _uiState.update {
                it.copy(moviesState = MovieUIState.Success(movies))
            }
        }
    }

    fun getCountAwards(personId: Int) = launchWithoutOld(GET_COUNT_AWARDS) {
        if (uiState.value.countAwards == null) return@launchWithoutOld

        val params = AwardParams(id = personId)

        val res = getPersonAwardsByDate.execute(params)

        res.onSuccess { awards ->
            if (awards.isNotEmpty()) {
                _uiState.update { it.copy(countAwards = awards.size) }
            }
        }
    }

    fun getSpouses(list: List<Int>) = launchWithoutOld(GET_SPOUSES) {
        if (uiState.value.personSpouseState is PersonUIState.Success) return@launchWithoutOld

        val temp = multiRequest(list.map { it.toString() }) {
            getPersonById.execute(it.toInt())
        }

        if (temp.isNotEmpty()) {
            _uiState.update {
                it.copy(personSpouseState = PersonUIState.Success(temp))
            }
        }
    }

    private suspend fun getGroups(movies: List<ShortMovie>) {
        _uiState.update {
            it.copy(groups = groupShortMovie.execute(movies))
        }

        _uiState.update {
            it.copy(groupsKeys = uiState.value.groups.map { movie -> movie.key })
        }

        if (uiState.value.groups.isNotEmpty()) {
            val currentKey = uiState.value.groupsKeys[0]

            _uiState.update {
                it.copy(moviesFromGroup = uiState.value.groups[currentKey] ?: listOf())
            }
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_PERSON_JOB = "get_person"
        const val GET_MOVIES_JOB = "get_movies"
        const val GET_COUNT_AWARDS = "get_awards"
        const val GET_SPOUSES = "get_spouses"
    }
}