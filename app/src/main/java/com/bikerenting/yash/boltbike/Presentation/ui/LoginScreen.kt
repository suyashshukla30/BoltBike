package com.bikerenting.yash.boltbike.Presentation.ui

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bikerenting.yash.boltbike.Presentation.BackgroundWhite
import com.bikerenting.yash.boltbike.Presentation.DividerGray
import com.bikerenting.yash.boltbike.Presentation.LightGray
import com.bikerenting.yash.boltbike.Presentation.TextSecondary
import com.bikerenting.yash.boltbike.R
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import com.bikerenting.yash.boltbike.Presentation.BrightOrange
import com.bikerenting.yash.boltbike.Presentation.ButtonTextWhite
import com.bikerenting.yash.boltbike.Presentation.TextBlack
import com.bikerenting.yash.boltbike.Presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen() {
    val viewModel = remember { LoginViewModel() }

    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val isOtpPhase by viewModel.isOtpPhase.collectAsState()

    val context = LocalContext.current
    val activity = context as Activity
    LoginContent(
        phoneNumber = phoneNumber,
        onPhoneNumberChange = viewModel::onPhoneNumberChange,
        onSendOtpClick = { viewModel.startPhoneAuth(activity) },
        isOtpPhase = isOtpPhase
    )
}

@Composable
fun LoginContent(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onSendOtpClick: () -> Unit,
    isOtpPhase: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val borderColor = if (isFocused) BrightOrange else LightGray

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundWhite)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Image(
                painter = painterResource(id = R.drawable.main_icon_inverse),
                contentDescription = "RideOn Logo",
                modifier = Modifier
                    .size(110.dp)
                    .scaleInAnimation()
            )

            Text(
                text = stringResource(R.string.app_name),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = TextBlack
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = if (isOtpPhase) "verify" else stringResource(R.string.login),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextBlack
            )

            if (!isOtpPhase) {
                PhoneNumberInput(phoneNumber, onPhoneNumberChange, interactionSource, borderColor)
            } else {
                // We'll build OTP input here later
            }

            Button(
                onClick = onSendOtpClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BrightOrange,
                    contentColor = ButtonTextWhite
                )
            ) {
                Text(if (isOtpPhase) "Verify" else "Send OTP", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PhoneNumberInput(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    interactionSource: MutableInteractionSource,
    borderColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, borderColor, RoundedCornerShape(12.dp))
            .background(color = BackgroundWhite)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "IN (+91)",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = TextBlack
        )

        Spacer(modifier = Modifier.width(12.dp))

        Divider(
            color = DividerGray,
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .width(1.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            placeholder = { Text("Enter phone number", color = TextSecondary) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            interactionSource = interactionSource,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
