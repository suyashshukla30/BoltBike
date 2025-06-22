package com.bikerenting.yash.boltbike.Core

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.bikerenting.yash.boltbike.Domain.Model.Locations
import com.google.android.gms.location.LocationServices

object LocationHelper {
    fun getCurrentLocation(context: Context, callback: (Locations?) -> Unit) {
        val client = LocationServices.getFusedLocationProviderClient(context)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            callback(null)
            return
        }

        client.lastLocation
            .addOnSuccessListener { androidLocation ->
                val domainLocation = androidLocation?.let {
                    Locations(
                        lat = it.latitude,
                        lng = it.longitude
                    )
                }
                callback(domainLocation)
            }
            .addOnFailureListener {
                callback(null)
            }
    }
}
