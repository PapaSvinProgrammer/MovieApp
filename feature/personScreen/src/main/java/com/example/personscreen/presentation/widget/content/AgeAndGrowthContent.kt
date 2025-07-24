package com.example.personscreen.presentation.widget.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.utils.convert.PrettyData

@Composable
internal fun AgeAndGrowthContent(
    age: Int?,
    growth: Int?
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        age?.let {
            val prettyAge = PrettyData.getPrettyAge(it)

            Text(
                text = prettyAge,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .size(3.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        shape = CircleShape
                    )
            )
        }

        growth?.let {
            val prettyGrowth = PrettyData.getPrettyGrowth(it)

            Text(
                text = prettyGrowth,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}