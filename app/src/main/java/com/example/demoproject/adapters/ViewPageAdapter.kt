package com.example.demoproject.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.demoproject.fragments.HomeFragment
import com.example.demoproject.fragments.NotificationsFragment
import com.example.demoproject.fragments.SettingFragment

class ViewPageAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
       return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                HomeFragment()
            }
            1->{
                NotificationsFragment()
            }
            2->{
                SettingFragment()
            }
            else->{
                HomeFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Home"
            }
            1 -> {
                return "Notification"
            }
            2 -> {
                return "Setting"
            }
        }
        return super.getPageTitle(position)
    }

}