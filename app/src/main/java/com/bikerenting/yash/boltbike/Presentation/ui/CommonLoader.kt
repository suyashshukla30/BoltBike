package com.bikerenting.yash.boltbike.Presentation.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bikerenting.yash.boltbike.Presentation.ButtonTextWhite

class CommonLoader {
    @Composable
    fun LoaderButtonContent(
        isLoading: Boolean,
        text: String,
        textColor: Color = ButtonTextWhite,
        loaderColor: Color = ButtonTextWhite
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = loaderColor,
                strokeWidth = 2.dp,
                modifier = Modifier.size(22.dp)
            )
        } else {
            Text(text, fontSize = 16.sp, color = textColor)
        }
    }
}