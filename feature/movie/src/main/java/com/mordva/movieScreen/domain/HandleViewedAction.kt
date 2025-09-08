package com.mordva.movieScreen.domain

import com.mordva.domain.repository.ViewedRepository
import com.mordva.model.local.PackageParams
import com.mordva.movieScreen.domain.model.CheckedParams
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class HandleViewedAction @Inject constructor(
    private val repository: ViewedRepository
) : UseCase<CheckedParams, Unit>(Dispatchers.IO) {
    override suspend fun run(params: CheckedParams) {
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