package com.bikerenting.yash.boltbike.Presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bikerenting.yash.boltbike.Core.MyApp
import com.bikerenting.yash.boltbike.Data.Local.UserEntity
import com.bikerenting.yash.boltbike.Data.Remote.ApiClient.apiService
import com.bikerenting.yash.boltbike.Presentation.views.MainNavigationActivity
import com.bikerenting.yash.boltbike.R
import com.bikerenting.yash.boltbike.databinding.ActivityUserProfileBinding
import kotlinx.coroutines.launch


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
        val user = UserRequest(
            uid = phone,
            phoneNumber = phone,
            name = name,
            email = email
        )

        //Delicate Api call
        lifecycleScope.launch {
            try {
                val response = apiService.registerNewUser(
                    token = "Bearer $firebaseToken",
                    user = user
                )
                if (response.body()!!.contains("registered successfully")) {
                    val intent = Intent(this@UserProfile, MainNavigationActivity::class.java)
                    (applicationContext as MyApp).database.appDao().insertUser(user.toEntity())
                    this@UserProfile.startActivity(intent)
                    this@UserProfile.finish()
                } else {
                    Log.e("API_suyash", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API_suyash", "Exception: ${e.localizedMessage}")
            }
        }
    }
    fun UserRequest.toEntity(): UserEntity {
        return UserEntity(
            uid = this.uid,
            phoneNumber = this.phoneNumber,
            name = this.name,
            email = this.email
        )
    }

}