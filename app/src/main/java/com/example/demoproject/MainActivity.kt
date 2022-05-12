package com.example.demoproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoproject.activities.TabLayoutClass
import com.example.demoproject.databinding.ActivityMainBinding
import com.example.demoproject.utils.SharedPrefs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val sharedPreferences = SharedPrefs(this)
        sharedPreferences.editor
        if (sharedPreferences.login) {
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
                sharedPreferences.apply {
                    login = true
                    username = binding.TvEmail.text.toString()
                    password = binding.TvPassword.text.toString()
                }
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