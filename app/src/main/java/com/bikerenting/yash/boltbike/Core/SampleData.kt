package com.bikerenting.yash.boltbike.Core

import com.bikerenting.yash.boltbike.Domain.Model.Location
import com.bikerenting.yash.boltbike.Domain.Model.Vehicle

public class SampleData {
    val baseUrl = "http://192.168.1.1:8080"
    val sampleBikes = listOf(
        Vehicle(
            bikeId = "bike_001",
            model = "Suzuki Access",
            2.57,
            4.44,
            4.44,
            "hostel a01",
            "scooty",
            "DL2S4881",
            "Parkin 2",
            "avail",
            "https://images.unsplash.com/photo-1449426468159-d96dbf08f19f?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=458129",
        ), Vehicle(
            bikeId = "bike_091",
            model = "Suzuki Access",
            232.57,
            4.64,
            4.44,
            "hostel a01",
            "scooty",
            "DL2S4881",
            "Parkin 2",
            "avail",
            "https://images.unsplash.com/photo-1449426468159-d96dbf08f19f?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=458129",
        )
    )
}
