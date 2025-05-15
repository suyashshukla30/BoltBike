package com.bikerenting.yash.boltbike.Presentation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bikerenting.yash.boltbike.Presentation.BorderGray
import com.bikerenting.yash.boltbike.Presentation.SplashBackground
import com.bikerenting.yash.boltbike.Presentation.TextPrimary
import com.bikerenting.yash.boltbike.R

@Composable
fun SplashScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SplashBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bike_main_icon),
                contentDescription = "RideOn Logo",
                modifier = Modifier
                    .size(150.dp)
                    .scaleInAnimation()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.app_name),
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.punch_line),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary

            )
        }
    }
}


@Composable
fun Modifier.scaleInAnimation(): Modifier {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1.27f,
            animationSpec = tween(durationMillis = 2000, easing = EaseOutBack)
        )
    }
    return this.graphicsLayer {
        scaleX = scale.value
        scaleY = scale.value
    }
}
