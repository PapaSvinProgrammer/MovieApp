package com.mordva.ui.widget.packageBottomSheet

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mordva.model.PackageType
import com.mordva.model.movie.Movie
import com.mordva.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackageBottomSheet(
    movie: Movie,
    packageSize: Map<PackageType, Int>,
    selectedSet: Set<PackageType>,
    onAction: (PackageItemAction) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val listItems = listOf(
        PackageItem(
            title = "Любимые фильмы",
            type = PackageType.FAVORITE
        ),
        PackageItem(
            title = "Буду смотреть",
            type = PackageType.WILL_WATCH
        )
    )

    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        listItems.forEach { packageItem ->
            val isSelected = packageItem.type in selectedSet

            ListItem(
                modifier = Modifier.clickable {
                    if (!isSelected) {
                        onAction(PackageItemAction.Add(packageItem.type))
                    } else {
                        onAction(PackageItemAction.Delete(packageItem.type))
                    }
                },
                leadingContent = {
                    Icon(
                        imageVector = packageItem.type.toIcon(isSelected),
                        contentDescription = null,
                        tint = if (isSelected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                },
                headlineContent = {
                    Text(
                        text = packageItem.title,
                        fontSize = Typography.bodyMedium.fontSize
                    )
                },
                trailingContent = {
                    Text(
                        text = packageSize[packageItem.type].toString()
                    )
                },
                colors = ListItemDefaults.colors(MaterialTheme.colorScheme.surfaceContainerLow)
            )

            HorizontalDivider()
        }
    }
}
