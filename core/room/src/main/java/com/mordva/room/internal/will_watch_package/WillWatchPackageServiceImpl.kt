package com.mordva.room.internal.will_watch_package

import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import com.mordva.room.external.WillWatchPackageService
import com.mordva.room.internal.utils.toDomain
import com.mordva.room.internal.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class WillWatchPackageServiceImpl @Inject constructor(
    private val dao: WillWatchPackageDao
) : WillWatchPackageService {
    override suspend fun insert(value: PackageParams) {
        dao.insert(value.toEntity())
    }

    override suspend fun delete(movieId: Int) {
        dao.delete(movieId)
    }

    override fun getByDateDesc(): Flow<List<MoviePackage>> {
        return dao.getSortByDateDesc().map { it.toDomain() }
    }

    override fun getByDateAsc(): Flow<List<MoviePackage>> {
        return dao.getSortByDateAsc().map { it.toDomain() }
    }

    override fun isStock(movieId: Int): Flow<MoviePackage?> {
        return dao.isStock(movieId).map { it?.toDomain() }
    }
}
