package com.mordva.room.internal.entities.blocked

import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import com.mordva.room.external.BlockedService
import com.mordva.room.internal.utils.toBlockedEntity
import com.mordva.room.internal.utils.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class BlockedServiceImpl @Inject constructor(
    private val dao: BlockedDao
) : BlockedService {
    override suspend fun insert(params: PackageParams) {
        dao.insert(params.toBlockedEntity())
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

    override fun count(): Flow<Int> {
        return dao.count()
    }
}