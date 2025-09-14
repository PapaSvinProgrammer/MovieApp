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
fun SelectableMultiDropDownList(
    modifier: Modifier = Modifier,
    current: List<DropDownItem>,
    list: List<DropDownItem>,
    onClick: (DropDownItem) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.padding(15.dp)) {
        SingleDropDownListItem(
            item = DropDownItem(text = current.joinToString { it.text }),
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
                    list.forEach { item ->
                        MultiDropDownListItem(
                            item = item,
                            isSelect = item in current,
                            onClick = { onClick(item) }
                        )
                    }
                }
            }
        }
    }
}
