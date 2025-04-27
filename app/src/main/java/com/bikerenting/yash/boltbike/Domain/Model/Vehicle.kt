package com.bikerenting.yash.boltbike.Domain.Model

data class Vehicle(
    val bikeId: String,
    val type: String,
    val brand: String,
    val numberPlate: String,
    val currentLocation: Location,
    val status: String,
    val pricePerKm: Double,
    val imageUrl: String
)
data class Location(
    val lat: Double,
    val lng: Double,
    val parkingArea: String
)