package com.example.demoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.demoproject.activities.TabLayoutClass
import com.example.demoproject.databinding.ActivityMainBinding
import com.example.demoproject.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("userCredentials", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (sharedPreferences.contains("isLogin")) {
            callIntent()
        } else {
            Toast.makeText(this, "Login to proceed", Toast.LENGTH_SHORT).show()
        }
        binding.btnLogin.setOnClickListener {
            if (
                binding.TvEmail.text.toString().trim().isNotEmpty() &&
                binding.TvPassword.text.toString().trim().isNotEmpty()
            ) {
                callIntent()
                editor.putString("isLogin", "User is logged in")
                editor.putString("username",binding.TvEmail.text.toString())
                editor.putString("password",binding.TvPassword.text.toString())
                editor.commit()
            } else {
                Toast.makeText(this, "Please Enter Username and password", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun callIntent() {
        Intent(this, TabLayoutClass::class.java).run {
            startActivity(this)
        }
    }


}