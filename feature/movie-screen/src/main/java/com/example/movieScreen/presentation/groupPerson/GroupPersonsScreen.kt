package com.example.movieScreen.presentation.groupPerson

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieScreen.domain.model.PersonMovieExtended
import com.example.movieScreen.presentation.groupPerson.widget.GroupUiState
import com.example.movieapp.ui.R
import com.example.navigationroute.PersonRoute
import com.example.navigationroute.model.PersonMovieScreenObject
import com.example.ui.widget.component.BasicLoadingBox
import com.example.ui.widget.listItems.PersonListItem
import com.example.ui.widget.other.TitleTopBarText
import com.example.utils.convert.PrettyData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GroupPersonsScreen(
    navController: NavController,
    list: List<PersonMovieScreenObject>,
    viewModel: GroupPersonViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.getGroupedPersons(list)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(R.string.persons))
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
        when (val state = uiState) {
            GroupUiState.Loading -> BasicLoadingBox()
            is GroupUiState.Success -> {
                MainContent(
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                    map = state.data,
                    onClick = { person ->
                        navController.navigate(PersonRoute(person.id)) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    map: Map<String, List<PersonMovieExtended>>,
    onClick: (PersonMovieExtended) -> Unit
) {
    LazyColumn(modifier = modifier) {
        map.forEach { key, value ->
            stickyHeader {
                Text(
                    text = PrettyData.getPrettyString(value.first().profession.toString()),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(15.dp)
                )
            }

            items(value) { person ->
                val profession = if (key == "actor")
                    person.description
                else
                    person.profession

                PersonListItem(
                    name = person.name,
                    enName = person.enName,
                    age = person.age,
                    birthday = person.birthday,
                    photo = person.photo,
                    professions = listOf(profession ?: ""),
                    onClick = { onClick(person) }
                )

                HorizontalDivider()
            }
        }

        item {
            Spacer(modifier = Modifier.height(110.dp))
        }
    }
}
