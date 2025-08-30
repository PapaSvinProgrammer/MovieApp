package com.mordva.account.presentation.widget.content

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.movieapp.ui.R
import com.mordva.security.internal.util.SecurityType

@Composable
internal fun AuthIconType(
    modifier: Modifier = Modifier,
    type: SecurityType
) {
    val icon = when (type) {
        SecurityType.YANDEX -> R.drawable.ic_yandex_logo
        SecurityType.VK -> R.drawable.ic_vk_logo
        SecurityType.PASSWORD -> R.drawable.ic_image
    }

    Icon(
        modifier = modifier,
        imageVector = ImageVector.vectorResource(icon),
        contentDescription = null,
        tint = Color.Unspecified
    )
}