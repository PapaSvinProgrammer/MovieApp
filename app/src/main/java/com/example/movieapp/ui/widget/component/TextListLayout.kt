package com.example.movieapp.ui.widget.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.movieapp.R
import com.example.movieapp.ui.widget.other.TitleTopBarText
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextListLayout(
    visible: Boolean,
    hazeState: HazeState,
    onClose: () -> Unit,
    title: String,
    list: List<String>,
    onResult: (List<String>) -> Unit
) {
    val result = remember { mutableStateListOf<Pair<String, Boolean>>() }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        list.forEach { result.add(it to false) }
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it })
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        TitleTopBarText(text = title)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onClose()
                                result.reset()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        TextButton(
                            onClick = {
                                result.reset()
                                onResult(listOf())
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.reset),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box {
                MainContent(
                    innerPadding = innerPadding,
                    hazeState = hazeState,
                    result = result,
                    onClick = {
                        result[it] = result[it].copy(
                            second = !result[it].second
                        )
                    }
                )

                SuccessButton(
                    isEnabled = result.any { it.second },
                    onClick = {
                        val res = result.filter { it.second }.map { it.first }
                        onResult(res)
                        onClose()
                    }
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    innerPadding: PaddingValues,
    hazeState: HazeState,
    result: List<Pair<String, Boolean>>,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .haze(hazeState)
        ) {
            items(result.size) { index ->
                ListItem(
                    headlineContent = {
                        Text(text = result[index].first)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onClick(index) },
                    trailingContent = {
                        if (result[index].second) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null
                            )
                        }
                    }
                )

                HorizontalDivider()
            }

            item {
                Spacer(modifier = Modifier.height(130.dp))
            }
        }
    }
}

@Composable
private fun BoxScope.SuccessButton(
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(30.dp)
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        onClick = onClick,
        enabled = isEnabled
    ) {
        Text(
            text = stringResource(R.string.save_settings),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun SnapshotStateList<Pair<String, Boolean>>.reset() {
    repeat(this.size) { index ->
        this[index] = this[index].copy(second = false)
    }
}