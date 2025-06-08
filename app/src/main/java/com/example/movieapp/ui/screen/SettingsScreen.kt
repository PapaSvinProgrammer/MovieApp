package com.example.movieapp.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.app.utils.ApplicationIcon
import com.example.movieapp.app.utils.iconsApplication
import com.example.movieapp.ui.viewModel.SettingsViewModel
import com.example.movieapp.ui.widget.other.TitleTopBarText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getTheme()
        viewModel.getCurrentIconIndex()
    }

    LaunchedEffect(viewModel.currentIcon) {
        if (viewModel.currentIcon != 1) {
            viewModel.updateAlternativeSwitch(true)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBarText(text = stringResource(R.string.settings))
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 15.dp,
                    end = 15.dp
                )
        ) {
            Text(
                text = stringResource(R.string.parent_control),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            TwinTitleRow(
                checked = viewModel.pinCodeSwitch,
                onClick = { viewModel.updatePinSwitch(it) },
                title = stringResource(R.string.enter_with_pin_code),
                description = stringResource(R.string.enter_with_pin_code_description)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.icon_app),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            OtherIconAppContent(
                checked = viewModel.alternativeIconSwitch,
                onClick = {
                    viewModel.updateAlternativeSwitch(it)

                    if (!it) {
                        viewModel.setCurrentIconIndex(1)
                    }
                },
                currentIconIndex = viewModel.currentIcon,
                onChangeIcon = {
                    viewModel.setCurrentIconIndex(it)
                }
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.theme),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            ChangeThemeContent(
                isDark = viewModel.isDark,
                onChangeTheme = { viewModel.setTheme(it) }
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.other),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            TwinTitleRow(
                checked = viewModel.vibrationSwitch,
                onClick = { viewModel.updateVibrationSwitch(it) },
                title = stringResource(R.string.vibration),
                description = stringResource(R.string.vibration_description)
            )
        }
    }
}

@Composable
private fun TwinTitleRow(
    checked: Boolean,
    onClick: (Boolean) -> Unit,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(5f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Switch(
            modifier = Modifier.weight(1f),
            checked = checked,
            onCheckedChange = onClick
        )
    }
}

@Composable
private fun OtherIconAppContent(
    checked: Boolean,
    onClick: (Boolean) -> Unit,
    onChangeIcon: (Int) -> Unit,
    currentIconIndex: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .animateContentSize()
    ) {
        OnceTitleRow(
            title = stringResource(R.string.alternative_icon),
            checked = checked,
            onClick = onClick
        )

        if (checked) {
            HorizontalDivider()
            Spacer(modifier = Modifier.height(15.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 10.dp)
            ) {
                itemsIndexed(iconsApplication) { index, item ->
                    IconCard(
                        icon = item,
                        currentIcon = currentIconIndex,
                        currentIndex = index,
                        onClick = { onChangeIcon(index + 1) }
                    )
                }
            }
        }
    }
}

@Composable
private fun IconCard(
    modifier: Modifier = Modifier,
    icon: ApplicationIcon,
    currentIcon: Int,
    currentIndex: Int,
    onClick: () -> Unit
) {
    val tint = if (icon.origColor)
        Color.Unspecified
    else
        MaterialTheme.colorScheme.onSurface

    val borderColor = if (currentIcon - 1 == currentIndex)
        MaterialTheme.colorScheme.primary
    else
        Color.Transparent

    Box(
        modifier = Modifier
            .size(80.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(icon.image),
            contentDescription = null,
            tint = tint,
            modifier = modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ChangeThemeContent(
    isDark: Boolean,
    onChangeTheme: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        ThemeCard(
            text = stringResource(R.string.dark),
            icon = painterResource(R.drawable.ic_dark),
            onClick = { onChangeTheme(true) },
            isFocus = isDark
        )

        ThemeCard(
            text = stringResource(R.string.light),
            icon = painterResource(R.drawable.ic_light),
            isFocus = !isDark,
            onClick = { onChangeTheme(false) }
        )
    }
}

@Composable
private fun ThemeCard(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
    isFocus: Boolean
) {
    val borderColor = if (isFocus) MaterialTheme.colorScheme.primary else Color.Transparent

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .height(130.dp)
            .width(110.dp)
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = icon,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = text,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun OnceTitleRow(
    checked: Boolean,
    title: String,
    onClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(5f),
            text = title,
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Switch(
            modifier = Modifier.weight(1f),
            checked = checked,
            onCheckedChange = onClick
        )
    }
}