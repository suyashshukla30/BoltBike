package com.bikerenting.yash.boltbike.Presentation.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.vector.ImageVector
import com.bikerenting.yash.boltbike.Presentation.viewmodel.MainListingViewModel
import com.bikerenting.yash.boltbike.R
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
                context, current_user
            )
        }
    }
}

@Composable
private fun HeaderView(context: Context, currentUser: FirebaseUser?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 18.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(alignment = Alignment.CenterStart)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (false) {
                    Image(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Default user icon",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(color = MaterialTheme.colorScheme.surface)
                        
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter("https://4kwallpapers.com/images/walls/thumbs_2t/19795.jpg"),
                        contentDescription = "User profile picture",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(color = MaterialTheme.colorScheme.surface)

                    )
                }
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

