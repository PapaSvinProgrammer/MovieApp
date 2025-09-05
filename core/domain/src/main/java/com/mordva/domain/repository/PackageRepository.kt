package com.mordva.domain.repository

import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import kotlinx.coroutines.flow.Flow

interface PackageRepository {
    suspend fun insert(params: PackageParams)
    suspend fun delete(movieId: Int)
    fun getByDateAsc(): Flow<List<MoviePackage>>
    fun getByDateDesc(): Flow<List<MoviePackage>>
    fun isStock(movieId: Int): Flow<MoviePackage?>
    fun count(): Flow<Int>
}