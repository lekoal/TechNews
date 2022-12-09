package com.private_projects.technews.data.vkdata

import com.private_projects.technews.BuildConfig

object VkHelpData {
    const val VK_API_KEY = BuildConfig.VK_API_KEY

    const val BASE_URL = "https://api.vk.com/method/"
    const val IXBT_ID = -39447662
    const val FERRA_ID = -24195542
    const val TDNEWS_ID = -14317987
    const val API_V = "5.131"
    const val PAGE_SIZE = 15 //Выбирать число, которое делится на количество ID без остатка
}