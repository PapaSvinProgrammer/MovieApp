package com.mordva.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.R
import com.mordva.model.movie.Fact
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow
import com.mordva.ui.widget.listItems.FactCard

internal fun LazyListScope.factsItem(facts: List<Fact>) {
    if (facts.isEmpty()) return

    item {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.facts_title),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)
        )

        DefaultLazyRow(
            list = facts,
            key = { it.value },
            lastItemCard = {}
        ) {
            FactCard(
                text = it.value,
                isSpoiler = it.spoiler ?: false,
                onClick = { }
            )
        }
    }
}