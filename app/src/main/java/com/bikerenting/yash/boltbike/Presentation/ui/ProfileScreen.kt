package com.bikerenting.yash.boltbike.Presentation.ui

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bikerenting.yash.boltbike.Presentation.viewmodel.MainListingViewModel
import com.bikerenting.yash.boltbike.Presentation.viewmodel.ProfileMainScreenViewModel
import com.bikerenting.yash.boltbike.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

val fontAwesome = FontFamily(Font(R.font.font_awesome_solid))

@Composable
fun ProfileScreen() {
    val profile_shared_data = remember { MainListingViewModel() }
    val context = LocalContext.current
    val current_user = FirebaseAuth.getInstance().currentUser
    val profileMainScreenViewModel = remember { ProfileMainScreenViewModel() }
    val dataAvailableList: List<Boolean> by profileMainScreenViewModel.isAvailableData.collectAsState()

    LaunchedEffect(Unit) {
        profileMainScreenViewModel.updateIsAvailableData()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column {
            HeaderView(
                context, current_user
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dataAvailableList.size) { index ->
                    DetailsCardView(if (dataAvailableList[index]) index else -1)
                }
            }
        }
    }
}


@Composable
fun DetailsCardView(index: Int) {
    if (index != -1) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 130.dp)
                .padding(bottom = 5.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Row(
                modifier = Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = (when (index) {
                        0 -> "\uf007"
                        1 -> "\uf2bb"
                        2 -> "\uf5a0"
                        3 -> "\uf21c"
                        4 -> "\uf555"
                        5 -> "\uf590"
                        else -> "We ran into a error..."
                    }),
                    fontFamily = fontAwesome,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.width(18.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = (when (index) {
                            0 -> "User Details"
                            1 -> "Legal Details"
                            2 -> "Addresses"
                            3 -> "Bookings"
                            4 -> "Wallet"
                            5 -> "Support"
                            else -> "We ran into a error..."
                        }),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = (when (index) {
                            0 -> "Edit your name, phone number, and other personal details"
                            1 -> "Add or update your Aadhaar, driving license, and other documents"
                            2 -> "Manage your saved addresses"
                            3 -> "View all your past and upcoming bookings"
                            4 -> "Check your wallet balance and transaction history"
                            5 -> "Need help? Chat with our support team"
                            else -> "We ran into a error..."
                        }),
                        maxLines = 2,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
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
                modifier = Modifier.fillMaxWidth(),
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
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(color = MaterialTheme.colorScheme.surface)

                    )
                }
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                        },
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

