package com.bikerenting.yash.boltbike.Presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {


    private val _isSplashDone = mutableStateOf(false)
    val isSplashDone: State<Boolean> = _isSplashDone
    init {
        viewModelScope.launch {
            delay(5000)
            _isSplashDone.value = true
        }
    }
}