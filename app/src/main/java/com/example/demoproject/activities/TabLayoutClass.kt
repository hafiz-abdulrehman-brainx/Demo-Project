package com.example.demoproject.activities

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.demoproject.R
import com.example.demoproject.adapters.ViewPageAdapter
import com.example.demoproject.databinding.ActivityTabLayoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutClass : AppCompatActivity() {
    private lateinit var binding: ActivityTabLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabLayoutBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        createViewPager()
    }

    private fun createViewPager() {
        binding.viewPager.adapter = ViewPageAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                if (position == 0)
                    tab.text = "Home"
                else if (position == 1)
                    tab.text = "Notification"
                else if (position == 2)
                    tab.text = "Setting"
                else
                    tab.text = "Home"
            }
        }.attach()
        binding.tabLayout.apply {
            getTabAt(0)?.setIcon(R.drawable.home)
            getTabAt(1)?.setIcon(R.drawable.notification)
            getTabAt(2)?.setIcon(R.drawable.settings)
        }
    }


}