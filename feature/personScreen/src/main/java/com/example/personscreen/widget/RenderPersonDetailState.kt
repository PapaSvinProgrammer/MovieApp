package com.example.personscreen.widget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.model.person.Person
import com.example.person.R
import com.example.ui.uiState.PersonUIState
import com.example.ui.widget.listItems.DetailInfoListItem
import com.example.ui.widget.shimmer.ShimmerPersonDetail
import com.example.utils.PrettyData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RenderPersonDetailState(state: PersonUIState) {
    when(state) {
        PersonUIState.Loading -> ShimmerPersonDetail()
        is PersonUIState.Success -> {
            MainPersonDetailContent(person = state.data.first())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MainPersonDetailContent(person: Person) {
    if (person.professions.isNotEmpty()) {
        DetailInfoListItem(
            header = stringResource(R.string.professions),
            trailing = person.professions.joinToString(", ")
        )
    }

    person.growth?.let {
        DetailInfoListItem(
            header = stringResource(R.string.growth),
            trailing = PrettyData.getPrettyGrowth(it)
        )
    }

    BirthdayDeathContent(
        birthday = person.birthday,
        death = person.death,
        age = person.age
    )

    if (person.birthPlace.isNotEmpty()) {
        DetailInfoListItem(
            header = stringResource(R.string.birth_place),
            trailing = person.birthPlace.joinToString(", ") { it.value }
        )
    }

    if (person.deathPlace.isNotEmpty()) {
        DetailInfoListItem(
            header = stringResource(R.string.death_place),
            trailing = person.deathPlace.joinToString(", ")
        )
    }

    person.countAwards?.let {
        DetailInfoListItem(
            header = stringResource(R.string.count_awards),
            trailing = it.toString()
        )
    }

    if (person.movies.isNotEmpty()) {
        DetailInfoListItem(
            header = stringResource(R.string.total_movies),
            trailing = person.movies.size.toString()
        )
    }


}