package com.bikerenting.yash.boltbike.Presentation.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class LoginViewModel : ViewModel() {

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber

    private val _otpCode = MutableStateFlow("")
    val otpCode: StateFlow<String> = _otpCode

    private val _isOtpPhase = MutableStateFlow(false)
    val isOtpPhase: StateFlow<Boolean> = _isOtpPhase

    private val _toastMessage = MutableSharedFlow<String?>()
    val toastMessage = _toastMessage.asSharedFlow()

    private var verificationId: String? = null

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    fun onPhoneNumberChange(newNumber: String) {
        _phoneNumber.value = newNumber
    }

    fun onOtpCodeChange(code: String) {
        _otpCode.value = code
    }

    fun startPhoneAuth(activity: Activity) {
        val phone = _phoneNumber.value
        _isLoading.value = true
        /*
        // do the work...
        delay(2000) // simulate network*/
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber("+91$phone")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    signInWithCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e("PhoneAuth", "Failed: ${e.localizedMessage}")
                    viewModelScope.launch {
                        _toastMessage.emit("Failed: ${e.localizedMessage}")
                    }
                }

                override fun onCodeSent(
                    id: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    verificationId = id
                    _isOtpPhase.value = true
                    viewModelScope.launch {
                        _toastMessage.emit("Success: OTP SENT")
                    }
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(activity: Activity) {
        val otp = _otpCode.value
        val id = verificationId
        _isLoading.value = true
/*        // do the work...
        delay(2000) // simulate network*/
        if (!otp.isNullOrBlank() && !id.isNullOrBlank()) {
            val credential = PhoneAuthProvider.getCredential(id, otp)
            signInWithCredential(credential)
        } else {
            Log.e("OTP", "Missing verification ID or OTP")
        }
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModelScope.launch {
                        _toastMessage.emit("WELCOME")
                    }
                } else {
                    _isOtpPhase.value = false
                    viewModelScope.launch {
                        _toastMessage.emit("Sign in failed: ${task.exception?.localizedMessage}")
                    }
                    Log.e("OTP", "Sign in failed: ${task.exception?.localizedMessage}")
                }
            }
    }
}
