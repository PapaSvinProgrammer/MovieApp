package com.example.search.widget.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.search.R
import com.example.search.widget.row.CategoryRow
import com.example.utils.convert.ConvertData

@Composable
internal fun DetailSettingsContent(
    genresResult: List<String>,
    countriesResult: List<String>,
    yearResult: Pair<Int, Int>,
    onGenreClick: () -> Unit,
    onCountryClick: () -> Unit,
    onYearClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 30.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
    ) {
        CategoryRow(
            index = 0,
            s = stringResource(R.string.genres),
            list = genresResult,
            defaultDescription = stringResource(R.string.any1),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )

        HorizontalDivider()

        CategoryRow(
            index = 1,
            s = stringResource(R.string.countries),
            list = countriesResult,
            defaultDescription = stringResource(R.string.any1),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )

        HorizontalDivider()

        CategoryRow(
            index = 2,
            s = stringResource(R.string.year),
            defaultDescription = ConvertData.convertYearRange(yearResult.first, yearResult.second),
            onCountryClick = onCountryClick,
            onGenreClick = onGenreClick,
            onYearClick = onYearClick
        )
    }
}