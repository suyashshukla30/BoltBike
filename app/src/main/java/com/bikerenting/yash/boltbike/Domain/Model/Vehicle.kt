package com.bikerenting.yash.boltbike.Domain.Model

data class Vehicle(
    val bikeId: String,
    val model: String,
    val pricePerKm: Double,
    val currentLat: Double,
    val currentLng: Double,
    val lastUpdatedAt: String,
    val type: String,
    val numberPlate: String,
    val locationName: String,
    val status: String,
    val imageUrl: String
)
