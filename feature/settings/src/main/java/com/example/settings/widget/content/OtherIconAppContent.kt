package com.example.settings.widget.content

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.settings.R
import com.example.settings.widget.card.IconCard
import com.example.settings.widget.iconsApplication
import com.example.settings.widget.row.OnceTitleRow

@Composable
internal fun OtherIconAppContent(
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