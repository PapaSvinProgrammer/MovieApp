package com.mordva.ui.widget.packageBottomSheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.movieapp.ui.R
import com.mordva.model.PackageType

@Composable
internal fun PackageType.toIcon(isSelected: Boolean): ImageVector {
    return when (this) {
        PackageType.FAVORITE -> {
            if (isSelected)
                ImageVector.vectorResource(R.drawable.ic_folder_star_fill)
            else
                ImageVector.vectorResource(R.drawable.ic_folder_star)
        }

        PackageType.WILL_WATCH -> {
            if (isSelected)
                ImageVector.vectorResource(R.drawable.ic_folder_eye_fill)
            else
                ImageVector.vectorResource(R.drawable.ic_folder_eye)
        }
    }
}