package com.private_projects.technews.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.tabs.TabLayoutMediator
import com.private_projects.technews.R
import com.private_projects.technews.databinding.ActivityMainBinding

const val ALL_NEWS_FRAGMENT = 0
const val IXBT_NEWS_FRAGMENT = 1
const val FERRA_NEWS_FRAGMENT = 2
const val TD_NEWS_FRAGMENT = 3

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                ALL_NEWS_FRAGMENT -> tab.text = resources.getString(R.string.all_news)
                IXBT_NEWS_FRAGMENT -> tab.text = resources.getString(R.string.ixbt_news)
                FERRA_NEWS_FRAGMENT -> tab.text = resources.getString(R.string.ferra_news)
                TD_NEWS_FRAGMENT -> tab.text = resources.getString(R.string.td_news)
            }
        }.attach()
    }
}