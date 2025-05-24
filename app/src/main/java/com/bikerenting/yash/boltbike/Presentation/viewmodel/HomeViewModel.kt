package com.bikerenting.yash.boltbike.Presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bikerenting.yash.boltbike.Domain.Model.Vehicle
import com.bikerenting.yash.boltbike.Data.Remote.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val _bikes = MutableStateFlow<List<Vehicle>>(emptyList())
    val bikes: StateFlow<List<Vehicle>> = _bikes
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
}