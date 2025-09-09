package com.mordva.ui.widget.packageBottomSheet

import com.mordva.model.PackageType

sealed interface PackageItemAction {
    data class Add(val type: PackageType) : PackageItemAction
    data class Delete(val type: PackageType) : PackageItemAction
}