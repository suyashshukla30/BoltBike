package com.bikerenting.yash.boltbike.Presentation.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.bikerenting.yash.boltbike.Core.SampleData
import com.bikerenting.yash.boltbike.Domain.Model.Vehicle
import com.bikerenting.yash.boltbike.Presentation.AppBackground
import com.bikerenting.yash.boltbike.Presentation.PrimaryOrange
import com.bikerenting.yash.boltbike.Presentation.TextPrimary
import com.bikerenting.yash.boltbike.Presentation.TextSecondary
import com.bikerenting.yash.boltbike.Presentation.viewmodel.MainListingViewModel
import com.bikerenting.yash.boltbike.R
import java.util.Objects

@Composable
fun HomeScreen() {
    val bike_list_ViewModel = remember { MainListingViewModel() }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column {
            HeaderView(
                context,
                user_name = "Yash",
                user_current_location = "Campus",
                user_filter_choice = 2,
                user_search = "Electric Bikes"
            )
            ListedBikes(
                context,
                SampleData().sampleBikes
            )
        }
    }
}

@Composable
fun ListedBikes(context: Context, sampleBikes: List<Vehicle>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sampleBikes.size) { bike ->
            BikeCard(sampleBikes[bike])
        }
    }
}

@Composable
fun BikeCard(vehicle: Vehicle) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(vehicle.imageUrl),
                contentDescription = vehicle.brand,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = vehicle.brand, fontSize = 20.sp, fontWeight = FontWeight.Bold)
               // Text(text = vehicle.type, color = Color.Gray)
                Text(text = "Plate: ${vehicle.numberPlate}", color = Color.DarkGray)
                Text(
                    text = "Parking: ${vehicle.currentLocation.parkingArea}",
                    color = Color.DarkGray
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "₹${vehicle.pricePerKm} / km",
                        color = Color.Green,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(32),
                        modifier = Modifier.padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
                    ) {
                        Text(
                            text = "Book",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }


                }
            }
        }
    }
}

@Composable
fun HeaderView(
    context: Context,
    user_name: String,
    user_current_location: String,
    user_filter_choice: Int,
    user_search: String
) {
    val fontAwesome = FontFamily(
        Font(R.font.font_awesome_solid) // assuming you placed fa_solid.otf in res/font/
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = AppBackground)
            .padding(horizontal = 24.dp), contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.align(alignment = Alignment.CenterStart)) {
            Text(
                textAlign = TextAlign.Start,
                text = user_name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(3.dp))

            Text(
                textAlign = TextAlign.Start,
                text = user_current_location,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(6.dp))

            var searchQuery = ""

            Row(
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .height(56.dp) // slightly bigger
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically // VERY IMPORTANT
            ) {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChanged = { searchQuery = it },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(52.dp) // little bigger button
                        .padding(start = 6.dp)
                        .background(
                            color = Color(0xFFF0F0F0),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            Toast.makeText(context, "gd", Toast.LENGTH_SHORT).show()
                        }
                ) {
                    Text(
                        text = "\uf0b0",
                        fontFamily = fontAwesome,
                        color = TextSecondary,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }


        }
    }
}

@Composable
fun SearchBar(
    searchQuery: String, onSearchQueryChanged: (String) -> Unit, modifier: Modifier = Modifier
) {
    val fontAwesome = FontFamily(Font(R.font.font_awesome_solid))

    BasicTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF0F0F0),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 10.dp),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "\uF002",
                    fontFamily = fontAwesome,
                    fontSize = 16.sp,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.width(12.dp))

                Box(Modifier.weight(1f)) {
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = "Search bikes, locations...",
                            color = TextSecondary,
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MainListScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        HeaderView(
            context = LocalContext.current,
            user_name = "Yash",
            user_current_location = "Campus",
            user_filter_choice = 2,
            user_search = "Electric Bikes"
        )
        ListedBikes(
            context = LocalContext.current,
            SampleData().sampleBikes
        )
    }
}

