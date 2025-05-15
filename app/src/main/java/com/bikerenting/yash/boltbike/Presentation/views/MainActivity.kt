package com.bikerenting.yash.boltbike.Presentation.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.bikerenting.yash.boltbike.Presentation.BoltBikeTheme
import com.bikerenting.yash.boltbike.Presentation.ui.LoginScreen
import com.bikerenting.yash.boltbike.Presentation.ui.SplashScreen
import com.bikerenting.yash.boltbike.Presentation.viewmodel.SplashViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BoltBikeTheme(dynamicColor = false)  {
                val isSplashDone by splashViewModel.isSplashDone

                Scaffold(modifier = Modifier.fillMaxSize()) {
                    if (!isSplashDone) {
                        SplashScreen()
                    } else {
                        val currentUser = FirebaseAuth.getInstance().currentUser
                        if(currentUser != null){
                            val intent = Intent(this, MainNavigationActivity::class.java)
                            startActivity(intent)
                            this.finish()
                        } else {
                            LoginScreen()
                        }
                    }
                }
            }
        }
    }
}
