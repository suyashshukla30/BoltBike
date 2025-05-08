package com.bikerenting.yash.boltbike.Presentation.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bikerenting.yash.boltbike.Presentation.AppBackground
import com.bikerenting.yash.boltbike.Presentation.viewmodel.MainListingViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


@Composable
fun ProfileScreen() {
    val profile_shared_data = remember { MainListingViewModel() }
    val context = LocalContext.current
    val current_user = FirebaseAuth.getInstance().currentUser
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column {
            HeaderView(
                context,
                current_user
            )
        }
    }
}

@Composable
private fun HeaderView(context: Context, currentUser: FirebaseUser?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = AppBackground)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(alignment = Alignment.CenterStart)
        ) {
            Image(painter = reme)
        }
    }
}

