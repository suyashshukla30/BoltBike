package com.bikerenting.yash.boltbike.Presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bikerenting.yash.boltbike.Domain.Model.MainNavigationItems
import com.bikerenting.yash.boltbike.Presentation.BackgroundWhite
import com.bikerenting.yash.boltbike.Presentation.BoltBikeTheme
import com.bikerenting.yash.boltbike.Presentation.ui.BookingsScreen
import com.bikerenting.yash.boltbike.Presentation.ui.ExploreScreen
import com.bikerenting.yash.boltbike.Presentation.ui.HomeScreen
import com.bikerenting.yash.boltbike.Presentation.ui.ProfileScreen
import com.bikerenting.yash.boltbike.R

class MainNavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            BoltBikeTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = MainNavigationItems.Home.route,
                        modifier = Modifier.padding(innerPadding)
                            .background(color = BackgroundWhite)
                    ) {
                        composable(MainNavigationItems.Home.route) { HomeScreen() }
                        composable(MainNavigationItems.Profile.route) { ProfileScreen() }
                    }
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        val items = listOf(
            MainNavigationItems.Home,
            MainNavigationItems.Profile
        )
        val fontAwesome = FontFamily(
            Font(R.font.font_awesome_solid) // assuming you placed fa_solid.otf in res/font/
        )
        NavigationBar {
            val currentBackStackEntry = navController.currentBackStackEntryAsState().value
            val currentRoute = currentBackStackEntry?.destination?.route

            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Text(
                            text = item.icon,
                            fontFamily = fontAwesome,
                            fontSize = 20.sp
                        )
                    },
                    label = { Text(text = item.label) }
                )
            }
        }
    }
}
