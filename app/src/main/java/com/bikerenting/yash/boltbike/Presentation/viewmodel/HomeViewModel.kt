package com.bikerenting.yash.boltbike.Presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bikerenting.yash.boltbike.Data.Local.DAO
import com.bikerenting.yash.boltbike.Domain.Model.Vehicle
import com.bikerenting.yash.boltbike.Data.Remote.ApiClient
import com.bikerenting.yash.boltbike.Domain.Model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userDao: DAO
) : ViewModel() {

    private val _bikes = MutableStateFlow<List<Vehicle>>(emptyList())
    val bikes: StateFlow<List<Vehicle>> = _bikes

    private val _userData = MutableStateFlow(User())
    val userData: StateFlow<User> = _userData

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
}
