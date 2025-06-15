package com.example.movieapp.ui.widget.renderState

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.widget.component.AwardListGroup
import com.example.movieapp.ui.widget.component.BasicLoadingBox
import com.example.movieapp.ui.widget.lazyComponent.EndlessLazyColumn
import com.example.network.module.person.NominationAward

@Composable
fun RenderAwardsContent(
    modifier: Modifier = Modifier,
    list: List<Pair<String, List<NominationAward>>>,
    onClick: (NominationAward) -> Unit,
    onLoadMore: () -> Unit
) {
    if (list.isEmpty()) {
        BasicLoadingBox(modifier)
    }
    else {
        MainAwardContent(
            modifier = modifier,
            awards = list,
            onClick = onClick,
            onLoadMore = onLoadMore
        )
    }
}

@Composable
private fun MainAwardContent(
    modifier: Modifier,
    awards: List<Pair<String, List<NominationAward>>>,
    onClick: (NominationAward) -> Unit,
    onLoadMore: () -> Unit
) {
    EndlessLazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(30.dp),
        items = awards,
        loadMore = onLoadMore
    ) { _, item ->
        AwardListGroup(
            title = item.first,
            awards = item.second,
            onClick = onClick
        )
    }
}