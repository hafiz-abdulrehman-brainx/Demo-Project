package com.example.demoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
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
        if(sharedPreferences.getBoolean("isLogin",false))
        {
            sendLoginDataToFragment()
            callIntent()
        }
        else{
            Toast.makeText(this,"Login to proceed",Toast.LENGTH_SHORT).show()
        }
        binding.btnLogin.setOnClickListener {
            if(binding.TvEmail != null && binding.TvPassword!=null){

                editor.putBoolean("isLogin",true)
                editor.commit()

                sendLoginDataToFragment()
                callIntent()

            }
            else{
                Toast.makeText(this,"Please Enter Username and password",Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun callIntent(){
        Intent(this, TabLayoutClass::class.java).run {
            startActivity(this)
        }
    }
    private fun sendLoginDataToFragment() {
        val bundle = Bundle()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val homeFragment = HomeFragment()
        bundle.putString("username",binding.TvEmail.toString())
        bundle.putString("password",binding.TvPassword.toString())
        homeFragment.arguments = bundle
        fragmentTransaction.commit()
    }
}