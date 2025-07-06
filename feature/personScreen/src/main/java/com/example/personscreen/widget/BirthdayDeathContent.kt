package com.example.personscreen.widget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.personScreen.R
import com.example.ui.widget.listItems.DetailInfoListItem
import com.example.ui.widget.other.PrettyAgeContent
import com.example.utils.FormatDate
import com.example.utils.PrettyData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun BirthdayDeathContent(
    birthday: String?,
    death: String?,
    age: Int?
) {
    if (birthday != null && death != null) {
        val prettyBirthday = FormatDate.formatDate(birthday)
        val prettyDeath = FormatDate.formatDate(death)

        DetailInfoListItem(
            header = stringResource(R.string.birthday),
            trailing = prettyBirthday
        )

        CustomDetailInfo(stringResource(R.string.death)) {
            PrettyAgeContent(
                modifier = Modifier.weight(2f),
                fontWeight = FontWeight.Medium,
                date = prettyDeath,
                age = PrettyData.getPrettyAge(age ?: 0)
            )
        }

        return
    }

    birthday?.let {
        val prettyBirthday = FormatDate.formatDate(birthday)

        CustomDetailInfo(stringResource(R.string.birthday)) {
            PrettyAgeContent(
                modifier = Modifier.weight(2f),
                date = prettyBirthday,
                age = PrettyData.getPrettyAge(age ?: 0)
            )
        }
    }
}

@Composable
private fun CustomDetailInfo(
    header: String,
    trailing: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = header,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth()
                .weight(1f)
        )

        trailing()
    }
}