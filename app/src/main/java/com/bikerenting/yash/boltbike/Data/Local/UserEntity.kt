package com.bikerenting.yash.boltbike.Data.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val uid: String,
    val phoneNumber: String,
    val name: String,
    val email: String
)