package com.mordva.account.presentation.widget.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.R
import com.mordva.ui.theme.Typography
import com.mordva.ui.widget.component.TitleRow

@Composable
internal fun SettingsList(
    onSound: () -> Unit,
    onConf: () -> Unit,
    onData: () -> Unit,
    onLanguage: () -> Unit,
    onDecor: () -> Unit,
    onSupport: () -> Unit,
    onAbout: () -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        SettingsContent(
            onSound = onSound,
            onConf = onConf,
            onData = onData,
            onLanguage = onLanguage,
            onTheme = onDecor
        )

        SupportContent(
            onSupport = onSupport,
            onAbout = onAbout
        )

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
private fun SettingsContent(
    onSound: () -> Unit,
    onConf: () -> Unit,
    onData: () -> Unit,
    onLanguage: () -> Unit,
    onTheme: () -> Unit,
) {
    Text(
        text = stringResource(R.string.settings),
        fontWeight = FontWeight.Bold,
        fontSize = Typography.bodyLarge.fontSize,
        modifier = Modifier.padding(start = 15.dp, bottom = 15.dp)
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        TitleRow(
            title = stringResource(R.string.notifications_and_sounds),
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            onClick = onSound,
            modifier = Modifier.clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp
                )
            )
        )
        HorizontalDivider()
        TitleRow(
            title = stringResource(R.string.confidentiality),
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            onClick = onConf

        )
        HorizontalDivider()
        TitleRow(
            title = stringResource(R.string.data_and_memory),
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            onClick = onData
        )
        HorizontalDivider()
        TitleRow(
            title = stringResource(R.string.decor),
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            onClick = onTheme
        )
        HorizontalDivider()
        TitleRow(
            title = stringResource(R.string.language),
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            onClick = onLanguage,
            modifier = Modifier.clip(
                RoundedCornerShape(
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
        )
    }
}

@Composable
private fun SupportContent(
    onSupport: () -> Unit,
    onAbout: () -> Unit
) {
    Text(
        text = stringResource(R.string.other),
        fontWeight = FontWeight.Bold,
        fontSize = Typography.bodyLarge.fontSize,
        modifier = Modifier
            .padding(
                horizontal = 15.dp,
                vertical = 15.dp
            )
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        TitleRow(
            title = stringResource(R.string.chat_with_support),
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            onClick = onSupport,
            modifier = Modifier.clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp
                )
            )
        )
        HorizontalDivider()
        TitleRow(
            title = stringResource(R.string.about_app),
            fontSize = Typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Normal,
            onClick = onAbout,
            modifier = Modifier.clip(
                RoundedCornerShape(
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
        )
    }
}