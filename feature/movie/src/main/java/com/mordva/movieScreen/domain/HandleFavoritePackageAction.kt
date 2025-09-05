package com.mordva.movieScreen.domain

import com.mordva.domain.repository.FavoritePackageRepository
import com.mordva.domain.repository.WillWatchPackageRepository
import com.mordva.model.PackageType
import com.mordva.model.local.PackageParams
import com.mordva.movieScreen.domain.model.PackageItemParams
import com.mordva.ui.widget.packageBottomSheet.PackageItemAction
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class HandleFavoritePackageAction @Inject constructor(
    private val willWatchPackageRepository: WillWatchPackageRepository,
    private val favoritePackageRepository: FavoritePackageRepository
) : UseCase<PackageItemParams, Unit>(Dispatchers.IO) {
    override suspend fun run(params: PackageItemParams) {
        when (params.action) {
            is PackageItemAction.Add -> addByType(params.action.type, params.movieId)
            is PackageItemAction.Delete -> deleteByType(params.action.type, params.movieId)
        }
    }

    private suspend fun addByType(type: PackageType, id: Int) {
        val params = PackageParams(
            id = id,
            date = System.currentTimeMillis()
        )

        when (type) {
            PackageType.FAVORITE -> favoritePackageRepository.insert(params)
            PackageType.WILL_WATCH -> willWatchPackageRepository.insert(params)
        }
    }

    private suspend fun deleteByType(type: PackageType, id: Int) {
        when (type) {
            PackageType.FAVORITE -> favoritePackageRepository.delete(id)
            PackageType.WILL_WATCH -> willWatchPackageRepository.delete(id)
        }
    }
}