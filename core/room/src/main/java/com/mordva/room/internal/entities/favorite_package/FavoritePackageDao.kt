package com.mordva.room.internal.entities.favorite_package

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FavoritePackageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoritePackageEntity)

    @Query("DELETE FROM favorite_package WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)

    @Transaction
    @Query("SELECT * FROM favorite_package ORDER BY date DESC")
    fun getSortByDateDesc(): Flow<List<FavoritePackageDetails>>

    @Transaction
    @Query("SELECT * FROM favorite_package ORDER BY date ASC")
    fun getSortByDateAsc(): Flow<List<FavoritePackageDetails>>

    @Query("SELECT * FROM favorite_package WHERE movie_id = :movieId")
    fun isStock(movieId: Int): Flow<FavoritePackageEntity?>

    @Query("SELECT COUNT(*) FROM favorite_package")
    fun count(): Flow<Int>
}