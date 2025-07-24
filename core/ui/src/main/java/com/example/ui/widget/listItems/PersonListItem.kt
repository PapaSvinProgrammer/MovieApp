package com.example.ui.widget.listItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.model.person.Person
import com.example.movieapp.ui.R
import com.example.utils.convert.FormatDate

@Composable
fun PersonListItem(
    person: Person,
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
            model = ImageRequest.Builder(LocalContext.current)
                .data(person.photo)
                .crossfade(true)
                .build(),
            contentDescription = null,
            error = painterResource(R.drawable.ic_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(130.dp)
                .width(90.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(15.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)) {
            InfoColumn(person)
            BottomContent(person, professions)
            TrailingIcon()
        }
    }
}

@Composable
private fun InfoColumn(person: Person) {
    Column {
        Text(
            text = person.name ?: "",
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )

        person.enName?.let {
            Text(
                text = person.enName ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        AgeContent(person)
    }
}

@Composable
private fun AgeContent(person: Person) {
    person.birthday?.let {
        Text(
            text = FormatDate.formatDate(it),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun BoxScope.BottomContent(person: Person, professions: List<String>) {
    var text = ""

    if (professions.isNotEmpty()) {
        text = professions.joinToString(", ")
    }

    person.sex?.let {
        text = "Пол: ${person.sex}"
    }

    Text(
        modifier = Modifier.align(Alignment.BottomStart),
        text = text,
        fontSize = 13.sp,
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