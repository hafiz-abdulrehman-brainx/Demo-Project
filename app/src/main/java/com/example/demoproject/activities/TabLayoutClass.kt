package com.example.demoproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.demoproject.R
import com.example.demoproject.adapters.ViewPageAdapter
import com.example.demoproject.databinding.ActivityTabLayoutBinding

class TabLayoutClass : AppCompatActivity() {
   lateinit var binding: ActivityTabLayoutBinding
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabLayoutBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPageAdapter(supportFragmentManager)
        binding.tabLayout.apply {
           setupWithViewPager(binding.viewPager)
           getTabAt( 0)?.setIcon(R.drawable.home)
           getTabAt(1)?.setIcon(R.drawable.notification)
           getTabAt(2)?.setIcon(R.drawable.settings)
        }


    }

}