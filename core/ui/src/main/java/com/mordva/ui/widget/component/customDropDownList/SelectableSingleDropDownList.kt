package com.mordva.ui.widget.component.customDropDownList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun SelectableSingleDropDownList(
    current: DropDownItem,
    list: List<DropDownItem>,
    onClick: (DropDownItem) -> Unit
) {
    val refactorList = remember(current) { list.weedOutList(current) }
    var isExpanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(15.dp)) {
        SingleDropDownListItem(
            item = current,
            isSelect = true,
            onClick = { isExpanded = !isExpanded }
        )

        Popup(onDismissRequest = { isExpanded = false }) {
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(expandFrom = Alignment.Top),
                exit = shrinkVertically(shrinkTowards = Alignment.Top)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .width(IntrinsicSize.Min)
                ) {
                    refactorList.forEach { item ->
                        SingleDropDownListItem(
                            item = item,
                            onClick = {
                                onClick(item)
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}


private fun List<DropDownItem>.weedOutList(current: DropDownItem): List<DropDownItem> {
    val res = mutableListOf<DropDownItem>()

    forEach { item ->
        if (item != current) {
            res.add(item)
        }
    }

    return res
}


