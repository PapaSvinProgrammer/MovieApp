package com.example.movieapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.core.utils.ConvertData
import com.example.core.utils.FormatDate
import com.example.movieapp.R
import com.example.movieapp.app.navigation.PersonRoute
import com.example.movieapp.ui.uiState.PersonUIState
import com.example.movieapp.ui.widget.listItems.DetailInfoListItem
import com.example.movieapp.ui.widget.listItems.SpouseCard
import com.example.movieapp.ui.widget.other.PrettyAgeContent
import com.example.movieapp.ui.widget.other.TitleTopBarText
import com.example.movieapp.ui.widget.shimmer.ShimmerPersonDetail
import com.example.movieapp.viewModels.PersonViewModel
import com.example.network.module.person.Person
import com.example.network.module.person.Spouse

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetailScreen(
    navController: NavController,
    viewModel: PersonViewModel,
    id: Int
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getPerson(id)
    }

    LaunchedEffect(viewModel.personState) {
        (viewModel.personState as? PersonUIState.Success)?.data?.let { persons ->
            viewModel.getSpouses(persons.first().spouses.map { it.id })
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(
                        text = (viewModel.personState as? PersonUIState.Success)
                            ?.data
                            ?.first()
                            ?.name ?: ""
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            RenderPersonDetailState(viewModel.personState)

            (viewModel.personState as? PersonUIState.Success)?.data?.let { data ->
                SpouseContent(
                    state = viewModel.personSpouseState,
                    spouses = data.first().spouses,
                    onClick = {
                        navController.navigate(PersonRoute(it)) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

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
            trailing = ConvertData.getPrettyGrowth(it)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun BirthdayDeathContent(
    birthday: String?,
    death: String?,
    age: Int?
) {
    if (birthday != null && death != null) {
        val prettyBirthday = FormatDate.formatDate(birthday)
        val prettyDeath = FormatDate.formatDate(death)

        DetailInfoListItem(
            header = stringResource(R.string.birthday),
            trailing = prettyBirthday
        )

        CustomDetailInfo(stringResource(R.string.death)) {
            PrettyAgeContent(
                modifier = Modifier.weight(2f),
                fontWeight = FontWeight.Medium,
                date = prettyDeath,
                age = ConvertData.getPrettyAge(age ?: 0)
            )
        }

        return
    }

    birthday?.let {
        val prettyBirthday = FormatDate.formatDate(birthday)

        CustomDetailInfo(stringResource(R.string.birthday)) {
            PrettyAgeContent(
                modifier = Modifier.weight(2f),
                date = prettyBirthday,
                age = ConvertData.getPrettyAge(age ?: 0)
            )
        }
    }
}

@Composable
private fun CustomDetailInfo(
    header: String,
    trailing: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = header,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth()
                .weight(1f)
        )

        trailing()
    }
}

@Composable
private fun SpouseContent(
    spouses: List<Spouse>,
    state: PersonUIState,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = stringResource(R.string.spouse),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        (state as? PersonUIState.Success)?.let {
            if (it.data.isEmpty()) {
                Text(
                    text = "-",
                    modifier = Modifier.fillMaxWidth().weight(2f)
                )
                return
            }

            Column(modifier = Modifier.weight(2f).fillMaxWidth()) {
                it.data.forEachIndexed { index, person ->
                    val spouse = spouses[index]

                    SpouseCard(
                        name = person.name ?: "",
                        countChild = spouse.children,
                        divorced = spouse.divorced ?: false,
                        photo = person.photo,
                        onClick = { onClick(person.id) }
                    )
                }
            }
        }
    }
}