package com.bikerenting.yash.boltbike.Data.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity):Long

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<UserEntity>

    @Query("UPDATE user SET lastLat = :lastLat, lastLng = :lastLng, locationTimestamp = :locationTimestamp WHERE uid = :uid")
    suspend fun updateLocation(uid: String, lastLat: Double, lastLng: Double, locationTimestamp: String): Int
}