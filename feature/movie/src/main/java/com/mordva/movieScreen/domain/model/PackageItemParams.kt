package com.mordva.movieScreen.domain.model

import com.mordva.ui.widget.packageBottomSheet.PackageItemAction

internal data class PackageItemParams(
    val action: PackageItemAction,
    val movieId: Int
)