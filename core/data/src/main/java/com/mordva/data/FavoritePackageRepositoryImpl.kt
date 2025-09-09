package com.mordva.data

import com.mordva.domain.repository.FavoritePackageRepository
import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import com.mordva.room.external.FavoritePackageService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class FavoritePackageRepositoryImpl @Inject constructor(
    private val service: FavoritePackageService
) : FavoritePackageRepository {
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
