package com.bikerenting.yash.boltbike.Presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bikerenting.yash.boltbike.Domain.Remote.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileMainScreenViewModel : ViewModel() {
    private val _isAvailableData = MutableStateFlow<List<Boolean>>(emptyList())
    val isAvailableData: StateFlow<List<Boolean>> = _isAvailableData
    fun updateIsAvailableData() {
        viewModelScope.launch {
            try {
                val response =  ApiClient.apiService.getAvailableProfileFeature()
                if(response.isSuccessful){
                    response.body()?.let {
                        _isAvailableData.value = it
                    }
                }
            } catch (e: Exception) {

            }
        }
    }
}