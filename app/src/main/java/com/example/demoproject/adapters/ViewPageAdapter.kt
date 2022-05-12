package com.example.demoproject.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demoproject.fragments.HomeFragment
import com.example.demoproject.fragments.NotificationsFragment
import com.example.demoproject.fragments.SettingFragment

class ViewPageAdapter(fm: FragmentManager,lifecycle: Lifecycle) : FragmentStateAdapter(fm,lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                NotificationsFragment()
            }
            2 -> {
                SettingFragment()
            }
            else -> {
                HomeFragment()
            }
        }
    }
    override fun getItemCount(): Int {
        return 3
    }
//
//    fun getPageTitle(position: Int): CharSequence? {
//        when (position) {
//            0 -> {
//                return "Home"
//            }
//            1 -> {
//                return "Notification"
//            }
//            2 -> {
//                return "Setting"
//            }
//        }
//        return "Home"
//    }



}