package com.example.demoproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.demoproject.activities.TabLayoutClass
import com.example.demoproject.databinding.ActivityMainBinding
import com.example.demoproject.model.User
import com.example.demoproject.repository.Repository
import com.example.demoproject.utils.SharedPrefs
import com.example.demoproject.viewModel.LoginViewModel
import com.example.demoproject.viewModel.LoginViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPrefs: SharedPrefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        sharedPrefs = SharedPrefs(this)
        initializeViewModel(sharedPrefs)
        setLoginObserver()

        binding.btnLogin.setOnClickListener {
            if (
                binding.TvEmail.text.toString().trim().isNotEmpty() &&
                binding.TvPassword.text.toString().trim().isNotEmpty()
            ){
                val user = User(email = binding.TvEmail.text.toString(), password = binding.TvPassword.text.toString())
                loginViewModel.loginUser(user)

            }
            else {
                Toast.makeText(this, "Please Enter Email and password", Toast.LENGTH_LONG).show()
            }
        }


    }
    private fun initializeViewModel(sharedPrefs: SharedPrefs) {
        val repository = Repository()
        val loginViewModel1: LoginViewModel by viewModels {
            LoginViewModelFactory(
                repository,
                sharedPrefs
            )
        }
        loginViewModel = loginViewModel1
    }
    private fun setLoginObserver() {
        loginViewModel.userResponse.observe(this){response ->
            if(response != null)
            {
                callIntent()
            }
        }
    }
    private fun callIntent() {
       Intent(this, TabLayoutClass::class.java).apply {
            startActivity(this)
        }
    }


}