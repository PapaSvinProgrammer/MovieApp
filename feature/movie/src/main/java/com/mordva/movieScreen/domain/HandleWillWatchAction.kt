package com.mordva.movieScreen.domain

import com.mordva.domain.repository.WillWatchPackageRepository
import com.mordva.model.local.PackageParams
import com.mordva.movieScreen.domain.model.WillWatchParams
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class HandleWillWatchAction @Inject constructor(
    private val repository: WillWatchPackageRepository
) : UseCase<WillWatchParams, Unit>(Dispatchers.IO) {
    override suspend fun run(params: WillWatchParams) {
        val packageParams = PackageParams(
            id = params.movieId,
            date = System.currentTimeMillis()
        )

        when (params.isChecked) {
            true -> repository.delete(params.movieId)
            false -> repository.insert(packageParams)
        }
    }
}
