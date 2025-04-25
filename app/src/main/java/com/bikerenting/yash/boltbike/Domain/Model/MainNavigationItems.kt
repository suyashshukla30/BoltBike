package com.bikerenting.yash.boltbike.Domain.Model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainNavigationItems(val route: String,
    val label: String, val icon: ImageVector){
        data object Home : MainNavigationItems("home", "Home", Icons.Default.Home)
        data object Explore : MainNavigationItems("explore", "Explore", Icons.Default.Search)
        data object Bookings : MainNavigationItems("bookings", "Bookings", Icons.Default.List)
        data object Profile : MainNavigationItems("profile", "Profile", Icons.Default.Person)

}