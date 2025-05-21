package com.bikerenting.yash.boltbike.Presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bikerenting.yash.boltbike.Domain.Remote.ApiClient.apiService
import com.bikerenting.yash.boltbike.Presentation.views.MainNavigationActivity
import com.bikerenting.yash.boltbike.R
import com.bikerenting.yash.boltbike.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject


class UserProfile : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserProfileBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val firebase_token = intent.getStringExtra("Firebase_login_token")
        binding.nameTextView.text = " Yash Shukla "
        binding.mobileTextView.text = "+91 8957177658"
        binding.emailTextView.text = "suyashshukla65@gmail.com"
        binding.submitButton.setOnClickListener {
            sendDataToBackendForUser(
                firebase_token,
                binding.nameTextView.text.toString(),
                binding.mobileTextView.text.toString(),
                binding.emailTextView.text.toString()
            )
        }
    }

    private fun sendDataToBackendForUser(
        firebaseToken: String?,
        name: String,
        phone: String,
        email: String
    ) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val uid = firebaseUser?.uid ?: throw IllegalStateException("No Firebase user")
        val user = UserRequest(
            uid = uid,
            phoneNumber = phone,
            name = name,
            email = email
        )

        //Delicate Api call
        GlobalScope.launch {
            try {
                val response = apiService.registerNewUser(
                    token = "Bearer $firebaseToken",
                    user = user
                )
                if (response.isSuccessful) {
                    val intent = Intent(this@UserProfile, MainNavigationActivity::class.java)
                    this@UserProfile.startActivity(intent)
                    this@UserProfile.finish()
                } else {
                    Toast.makeText(this@UserProfile, response.body()?.message, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("API_suyash", "Exception: ${e.localizedMessage}")
            }

        }
    }
}