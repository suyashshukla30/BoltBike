package com.bikerenting.yash.boltbike.Core

import com.bikerenting.yash.boltbike.Domain.Model.Location
import com.bikerenting.yash.boltbike.Domain.Model.Vehicle

public class SampleData {
    val sampleBikes = listOf(
        Vehicle(
            bikeId = "bike_001",
            type = "Scooter",
            brand = "Honda Activa",
            numberPlate = "DL5S1234",
            currentLocation = Location(28.61, 77.23, "Main Gate Parking"),
            status = "available",
            pricePerKm = 3.0,
            imageUrl = "https://images.unsplash.com/photo-1449426468159-d96dbf08f19f?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8bW90b3JiaWtlfGVufDB8fDB8fHww"
        ),
        Vehicle(
            bikeId = "bike_002",
            type = "Scooter",
            brand = "Honda Activa2",
            numberPlate = "DL5S1234",
            currentLocation = Location(28.61, 77.23, "Main Gate Parking"),
            status = "available",
            pricePerKm = 3.7,
            imageUrl = "https://images.unsplash.com/photo-1449426468159-d96dbf08f19f?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8bW90b3JiaWtlfGVufDB8fDB8fHww"
        )
    )
}