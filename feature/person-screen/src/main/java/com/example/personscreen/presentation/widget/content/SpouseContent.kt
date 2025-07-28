package com.example.personscreen.presentation.widget.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.person.Spouse
import com.example.movieapp.ui.R
import com.example.personscreen.presentation.widget.listItem.SpouseCard
import com.example.ui.uiState.PersonUIState

@Composable
internal fun SpouseContent(
    spouses: List<Spouse>,
    state: PersonUIState,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = stringResource(R.string.spouse),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        (state as? PersonUIState.Success)?.let {
            if (it.data.isEmpty()) {
                Text(
                    text = "-",
                    modifier = Modifier.fillMaxWidth().weight(2f)
                )
                return
            }

            Column(modifier = Modifier.weight(2f).fillMaxWidth()) {
                it.data.forEachIndexed { index, person ->
                    val spouse = spouses[index]

                    SpouseCard(
                        name = person.name ?: "",
                        countChild = spouse.children,
                        divorced = spouse.divorced ?: false,
                        photo = person.photo,
                        onClick = { onClick(person.id) }
                    )
                }
            }
        }
    }
}