package com.bikerenting.yash.boltbike.Presentation.viewmodel

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bikerenting.yash.boltbike.Core.LocationHelper
import com.bikerenting.yash.boltbike.Data.Local.DAO
import com.bikerenting.yash.boltbike.Domain.Model.Vehicle
import com.bikerenting.yash.boltbike.Data.Remote.ApiClient
import com.bikerenting.yash.boltbike.Domain.Model.Locations
import com.bikerenting.yash.boltbike.Domain.Model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.Locale

class HomeViewModel(
    private val userDao: DAO
) : ViewModel() {

    private val _bikes = MutableStateFlow<List<Vehicle>>(emptyList())
    val bikes: StateFlow<List<Vehicle>> = _bikes

    private val _userData = MutableStateFlow(User())
    val userData: StateFlow<User> = _userData
    private val _userLocation = MutableStateFlow<String>("")
    val address: StateFlow<String> = _userLocation

    fun getUserDetail() {
        viewModelScope.launch {
            try {
                userDao.getUser().collect { userEntity ->
                    _userData.value = User(
                        uid = userEntity.uid,
                        name = userEntity.name,
                        phoneNumber = userEntity.phoneNumber,
                        email = userEntity.email
                    )
                }
            } catch (e: Exception) {
                Log.e("UserDetail", "Exception: ${e.localizedMessage}")
            }
        }
    }

    fun getBikeList() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getAllBikes()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _bikes.value = it
                    }
                } else {
                    Log.e("BikeList", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("BikeList", "Exception: ${e.localizedMessage}")
            }
        }
    }

    fun isUserLocationStale(): Boolean {
        val user = _userData.value
        val locationTimestamp = user.locationTimestamp ?: return true
        val isLatLngZero = user.lastLat == 0.0 && user.lastLng == 0.0
        val isOld = true
        return isLatLngZero || isOld
    }

    fun fetchAndUpdateLocation(context: Context) {
        LocationHelper.getCurrentLocation(context) { location ->
            if (location != null) {
                val updatedUser = userData.value.copy(
                    lastLat = location.lat,
                    lastLng = location.lng,
                    locationTimestamp = getCurrentTimeISO()
                )
                viewModelScope.launch {
                    val address = getAddressFromLocation(
                        context,
                        updatedUser.lastLat!!,
                        updatedUser.lastLng!!
                    )
                    _userData.value = updatedUser
                    _userLocation.value = address!!
                    val rowsAffected = userDao.updateLocation(
                        uid = userData.value.uid,
                        lastLat = location.lat,
                        lastLng = location.lng,
                        locationTimestamp = userData.value.locationTimestamp ?: getCurrentTimeISO()
                    )
                }
            }
        }
    }

    fun getCurrentTimeISO(): String {
        return java.time.ZonedDateTime.now().toString()
    }

    fun getAddressFromLocation(context: Context, lat: Double, lng: Double): String? {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, lng, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                addresses[0].getAddressLine(0)  // full address line
            } else "H.N. 555/402, Bhola kheda, Alambagh Lucknow"
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
