package com.bikerenting.yash.boltbike.Domain.Model

data class User(
    val uid: String = "",
    val phoneNumber: String = "",
    val name: String? = "",
    val email: String? = "",
    val hostel: String? = "",
    val createdAt: String = "",
    val isAdmin: Boolean = false,
    val lastLat: Double? = 0.00,
    val lastLng: Double? = 0.00,
    val locationTimestamp: String? = "",
    val imageUrl: String? = ""
)
