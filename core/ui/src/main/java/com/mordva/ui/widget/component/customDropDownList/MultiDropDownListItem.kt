package com.mordva.ui.widget.component.customDropDownList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun MultiDropDownListItem(
    item: DropDownItem,
    isSelect: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(130.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(end = 20.dp)
        ) {
            item.icon?.let { icon ->
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = item.text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (isSelect) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = Icons.Rounded.Check,
                contentDescription = null
            )
        }
    }
}