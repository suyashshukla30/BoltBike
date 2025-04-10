// LoginViewModel.kt
package com.bikerenting.yash.boltbike.Presentation.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.TimeUnit

class LoginViewModel : ViewModel() {

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber

    private val _isOtpPhase = MutableStateFlow(false)
    val isOtpPhase: StateFlow<Boolean> = _isOtpPhase

    fun onPhoneNumberChange(newNumber: String) {
        _phoneNumber.value = newNumber
    }

    fun startPhoneAuth(activity: Activity) {
        val phone = _phoneNumber.value
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber("+91$phone")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e("PhoneAuth", "Failed: ${e.localizedMessage}")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    Log.d("PhoneAuth", "Code sent to $phone")
                    _isOtpPhase.value = true
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}
