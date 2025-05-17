package com.bikerenting.yash.boltbike.Presentation.ui

data class UserRequest(
    val uid: String,
    val phoneNumber: String,
    val name: String,
    val email: String
)