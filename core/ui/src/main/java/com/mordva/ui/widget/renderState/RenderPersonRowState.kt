package com.mordva.ui.widget.renderState

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mordva.model.person.Person
import com.mordva.ui.uiState.PersonUIState
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.LastItemCard
import com.mordva.ui.widget.listItems.PersonCard
import com.mordva.ui.widget.shimmer.ShimmerMovieRow

@Composable
fun RenderPersonRowState(
    state: PersonUIState,
    title: String,
    onClick: (Person) -> Unit,
    onShowAll: () -> Unit
) {
    when (state) {
        PersonUIState.Loading -> ShimmerMovieRow()
        is PersonUIState.Success -> {
            PersonRow(
                persons = state.data,
                title = title,
                onClick = onClick,
                onShowAll = onShowAll
            )
        }
    }
}

@Composable
private fun PersonRow(
    modifier: Modifier = Modifier,
    persons: List<Person>,
    title: String,
    onClick: (Person) -> Unit,
    onShowAll: () -> Unit
) {
    TitleRow(
        title = title,
        onClick = onShowAll
    )

    DefaultLazyRow(
        modifier = modifier,
        list = persons,
        lastItemCard = {
            LastItemCard(
                width = 160.dp,
                height = 250.dp,
                onClick = onShowAll
            )
        },
        content = {
            PersonCard(person = it) { onClick(it) }
        }
    )
}