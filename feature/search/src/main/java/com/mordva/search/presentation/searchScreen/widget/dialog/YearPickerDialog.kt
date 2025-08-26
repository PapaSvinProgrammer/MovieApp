package com.mordva.search.presentation.searchScreen.widget.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.R
import com.mordva.search.presentation.searchScreen.widget.lazyComponent.InfiniteCircularList
import com.mordva.search.presentation.searchScreen.widget.lazyComponent.calculateTargetIndex
import com.mordva.util.Constants.LAST_YEAR
import com.mordva.util.convert.FormatDate
import kotlinx.coroutines.launch

@Composable
fun YearPickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (start: Int, end: Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val initialItem = FormatDate.getCurrentYear()

    var yearsStart by remember { mutableStateOf((initialItem downTo LAST_YEAR).toList()) }
    var yearsEnd by remember { mutableStateOf((initialItem downTo LAST_YEAR).toList()) }

    var startResult by remember { mutableIntStateOf(initialItem) }
    var endResult by remember { mutableIntStateOf(initialItem) }

    val scrollStateStart = rememberLazyListState(0)
    val scrollStateEnd = rememberLazyListState(0)

    AlertDialog(
        title = { Text(text = stringResource(R.string.select_date)) },
        text = {
            Row(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PickerContent(
                    years = yearsStart,
                    initialItem = initialItem,
                    title = "с",
                    scrollState = scrollStateStart,
                    onItemSelected = {_, item ->
                        startResult = item
                        yearsEnd = (initialItem downTo startResult).toList()
                    }
                )

                PickerContent(
                    years = yearsEnd,
                    initialItem = initialItem,
                    title = "по",
                    scrollState = scrollStateEnd,
                    onItemSelected = {_, item ->
                        endResult = item
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(startResult, endResult) }
            ) {
                Text(text = stringResource(R.string.select))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    scope.launch {
                        yearsStart = (initialItem downTo LAST_YEAR).toList()
                        yearsEnd = (initialItem downTo LAST_YEAR).toList()

                        val target = calculateTargetIndex(yearsStart, 2025)
                        scrollStateStart.scrollToItem(target)
                        scrollStateEnd.scrollToItem(target)
                    }
                }
            ) { Text(text = stringResource(R.string.reset)) }
        },
        onDismissRequest = onDismiss
    )
}

@Composable
private fun <T> PickerContent(
    years: List<T>,
    initialItem: T,
    title: String,
    scrollState: LazyListState,
    onItemSelected: (index: Int, item: T) -> Unit
) {
    Column {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(5.dp))

        InfiniteCircularList(
            width = 100.dp,
            itemHeight = 70.dp,
            items = years,
            initialItem = initialItem,
            scrollState = scrollState,
            textStyle = TextStyle(fontSize = 14.sp),
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedTextColor = MaterialTheme.colorScheme.onSurface,
            onItemSelected = onItemSelected
        )
    }
}