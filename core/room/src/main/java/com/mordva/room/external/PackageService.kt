package com.mordva.room.external

import com.mordva.model.local.MoviePackage
import com.mordva.model.local.PackageParams
import kotlinx.coroutines.flow.Flow

interface PackageService {
    suspend fun insert(params: PackageParams)
    suspend fun delete(movieId: Int)
    fun getByDateDesc(): Flow<List<MoviePackage>>
    fun getByDateAsc(): Flow<List<MoviePackage>>
    fun isStock(movieId: Int): Flow<MoviePackage?>
    fun count(): Flow<Int>
}