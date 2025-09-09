package com.mordva.room.internal.entities.viewed

import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import com.mordva.room.external.ViewedService
import com.mordva.room.internal.utils.toDomain
import com.mordva.room.internal.utils.toViewedEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ViewedServiceImpl @Inject constructor(
    private val dao: ViewedDao
) : ViewedService {
    override suspend fun insert(params: PackageParams) {
        dao.insert(params.toViewedEntity())
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