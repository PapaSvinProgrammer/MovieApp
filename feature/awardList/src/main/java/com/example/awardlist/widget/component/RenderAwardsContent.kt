package com.example.awardlist.widget.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.model.person.NominationAward
import com.example.ui.widget.component.BasicLoadingBox
import com.example.ui.widget.lazyComponent.EndlessLazyColumn

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