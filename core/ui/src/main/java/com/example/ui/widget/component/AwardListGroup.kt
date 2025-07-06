package com.example.ui.widget.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.person.NominationAward
import com.example.movieapp.ui.R

@Composable
fun AwardListGroup(
    modifier: Modifier = Modifier,
    title: String,
    awards: List<NominationAward>,
    onClick: (NominationAward) -> Unit
) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 15.dp)
    )

    Spacer(modifier = Modifier.height(15.dp))

    awards.forEachIndexed { index, it ->
        AwardListItem(
            modifier = modifier.clickable(onClick = { onClick(it) }),
            title = it.nomination?.title ?: "",
            movieName = it.movie?.name,
            winning = it.winning ?: false
        )

        if (index != awards.lastIndex) {
            HorizontalDivider(modifier = Modifier.padding(start = 15.dp))
        }
    }
}

@Composable
fun AwardListItem(
    modifier: Modifier,
    title: String,
    movieName: String?,
    winning: Boolean
) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )

                movieName?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = if (winning)
                        stringResource(R.string.award)
                    else
                        stringResource(R.string.nomination),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null
            )
        }
    )
}