package com.example.movieapp.ui.widget.lazyComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.widget.listItems.PersonMovieListItem
import com.example.network.model.person.PersonMovie

@Composable
fun PersonGridHorizontalList(
    modifier: Modifier = Modifier,
    list: List<PersonMovie>,
    onClick: (PersonMovie) -> Unit
) {
    LazyHorizontalGrid(
        modifier = modifier.height(350.dp),
        rows = GridCells.Fixed(3)
    ) {
        items(list) {
            PersonMovieListItem(
                modifier = Modifier.clickable { onClick(it) },
                person = it
            )
        }
    }
}