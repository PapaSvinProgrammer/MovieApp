package com.mordva.data

import com.mordva.domain.repository.ViewedRepository
import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import com.mordva.room.external.ViewedService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ViewedRepositoryImpl @Inject constructor(
    private val service: ViewedService
) : ViewedRepository {
    override suspend fun insert(params: PackageParams) {
        service.insert(params)
    }

    override suspend fun delete(movieId: Int) {
        service.delete(movieId)
    }

    override fun getByDateAsc(): Flow<List<MoviePackage>> {
        return service.getByDateAsc()
    }

    override fun getByDateDesc(): Flow<List<MoviePackage>> {
        return service.getByDateDesc()
    }

    override fun isStock(movieId: Int): Flow<MoviePackage?> {
        return service.isStock(movieId)
    }

    override fun count(): Flow<Int> {
        return service.count()
    }
}
