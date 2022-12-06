package com.private_projects.technews.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.private_projects.technews.ui.allnews.AllNewsFragment
import com.private_projects.technews.ui.ferra.FerraNewsFragment
import com.private_projects.technews.ui.ixbt.IxbtNewsFragment
import com.private_projects.technews.ui.threednews.TDNewsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragments = arrayOf(
        AllNewsFragment(), IxbtNewsFragment(), FerraNewsFragment(), TDNewsFragment()
    )

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            ALL_NEWS_FRAGMENT -> fragments[ALL_NEWS_FRAGMENT]
            IXBT_NEWS_FRAGMENT -> fragments[IXBT_NEWS_FRAGMENT]
            FERRA_NEWS_FRAGMENT -> fragments[FERRA_NEWS_FRAGMENT]
            TD_NEWS_FRAGMENT -> fragments[TD_NEWS_FRAGMENT]
            else -> fragments[ALL_NEWS_FRAGMENT]
        }
    }
}