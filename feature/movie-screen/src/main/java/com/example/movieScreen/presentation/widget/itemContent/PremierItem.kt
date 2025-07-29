package com.example.movieScreen.presentation.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.totalValue.Premiere
import com.example.movieScreen.presentation.widget.component.PremierListContent
import com.example.movieapp.ui.R

internal fun LazyListScope.premierItem(premiere: Premiere?) {
    item {
        premiere?.let {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.rental),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )

            PremierListContent(it)
        }
    }
}