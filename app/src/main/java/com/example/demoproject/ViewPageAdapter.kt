package com.example.demoproject

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPageAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    @Suppress("DEPRECATION")
    internal class TabsAdapter(
        var context: Context,
        fm: FragmentManager,
        var totalTabs: Int,
    )
    override fun getCount(): Int {
       return 3
    }

    override fun getItem(position: Int): Fragment {
       when(position){
           0->{
                return HomeFragment()
           }
           1->{
               return NotificationsFragment()
           }
           2->{
               return SettingFragment()
           }
           else->{
               return HomeFragment()
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