package com.mordva.ui.widget.listItems

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movieapp.ui.R
import com.mordva.ui.theme.Typography
import com.mordva.util.convert.FormatDate
import com.mordva.util.convert.PrettyData

@Composable
fun PersonListItem(
    name: String?,
    enName: String?,
    age: Int?,
    birthday: String?,
    photo: String?,
    sex: String? = null,
    professions: List<String> = listOf(),
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(15.dp)
    ) {
        AsyncImage(
            model = photo,
            contentDescription = null,
            error = painterResource(R.drawable.ic_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(110.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(15.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                InfoColumn(
                    name = name,
                    enName = enName,
                    birthday = birthday,
                    age = age
                )

                BottomContent(
                    sex = sex,
                    professions = professions
                )
            }

            TrailingIcon()
        }
    }
}

@Composable
private fun InfoColumn(
    name: String?,
    enName: String?,
    birthday: String?,
    age: Int?
) {
    Column {
        Text(
            text = name ?: "",
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            fontSize = Typography.bodyMedium.fontSize
        )

        enName?.let {
            Text(
                text = enName,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = Typography.bodyMedium.fontSize
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        AgeContent(
            birthday = birthday,
            age = age
        )
    }
}

@Composable
private fun AgeContent(
    birthday: String?,
    age: Int?
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        birthday?.let {
            Text(
                text = FormatDate.formatDate(it),
                fontSize = Typography.bodySmall.fontSize,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        age?.let {
            Box(
                modifier = Modifier
                    .padding(horizontal = 7.dp)
                    .size(3.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        shape = CircleShape
                    )
            )

            Text(
                text = PrettyData.getPrettyAge(it),
                fontSize = Typography.bodySmall.fontSize,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun BottomContent(
    sex: String?,
    professions: List<String>
) {
    var text = ""

    if (professions.isNotEmpty()) {
        text = professions.joinToString(", ")
    }

    sex?.let {
        text = stringResource(R.string.sex, sex)
    }

    Text(
        text = text,
        fontSize = Typography.bodySmall.fontSize,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun BoxScope.TrailingIcon() {
    Icon(
        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
        contentDescription = null,
        modifier = Modifier.align(Alignment.CenterEnd),
        tint = MaterialTheme.colorScheme.onSurfaceVariant
    )
}