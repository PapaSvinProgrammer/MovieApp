package com.mordva.account.presentation.widget.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.movieapp.account.R
import com.mordva.account.domain.model.UserAccount
import com.mordva.account.presentation.widget.state.UserAccountState
import com.mordva.ui.theme.Typography

@Composable
internal fun ColumnScope.ProfileContent(
    state: UserAccountState
) {
    when (state) {
        is UserAccountState.Error -> ErrorContent()
        UserAccountState.Loading -> LoadingContent()
        is UserAccountState.Success -> SuccessContent(state.data)
    }
}

@Composable
private fun ColumnScope.SuccessContent(
    account: UserAccount
) {
    Box(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(20.dp)
    ) {
        AsyncImage(
            model = account.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .align(Alignment.Center)
        )

        AuthIconType(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 5.dp, end = 5.dp)
                .size(20.dp),
            type = account.securityType
        )
    }

    Text(
        text = account.name,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = account.email,
        fontSize = Typography.bodyMedium.fontSize,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ErrorContent() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.error_get_data),
            fontSize = Typography.bodyMedium.fontSize
        )
    }
}
