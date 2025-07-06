package com.example.personscreen.widget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.model.person.Person
import com.example.movieapp.personScreen.R
import com.example.ui.widget.other.BirthdayDepthContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPersonContent(
    person: Person,
    onClickDetail: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(person.photo)
                .crossfade(true)
                .build(),
            error = painterResource(R.drawable.ic_movie),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(140.dp)
                .height(210.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column(
            modifier = Modifier.defaultMinSize(minHeight = 210.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                NameContent(
                    name = person.name,
                    alternativeName = person.enName
                )

                Spacer(modifier = Modifier.height(10.dp))

                BirthdayDepthContent(
                    birthday = person.birthday,
                    death = person.death
                )

                AgeAndGrowthContent(
                    age = person.age,
                    growth = person.growth
                )
            }

            TextButton(
                contentPadding = PaddingValues(0.dp),
                onClick = onClickDetail
            ) {
                Text(text = stringResource(R.string.details))
            }
        }
    }
}

@Composable
private fun NameContent(
    name: String?,
    alternativeName: String?
) {
    Text(
        text = name ?: "",
        fontSize = 23.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 40.sp
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = alternativeName ?: "",
        fontSize = 14.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}