package com.bikerenting.yash.boltbike.Domain.Model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class MainNavigationItems(
    val route: String,
    val label: String, val icon: String
) {
    data object Home : MainNavigationItems("home", "Ride", "\uf21c")
    data object Profile : MainNavigationItems("profile", "Profile", "\uf007")

}