package com.private_projects.technews.data.newsapi

import com.private_projects.technews.BuildConfig

object NewsData {
    const val NEWS_API_KEY = BuildConfig.NEWS_API_KEY

    const val BASE_URL = "https://newsapi.org/v2/"
    const val IXBT_DOMAIN = "ixbt.com"
    const val FERRA_DOMAIN = "ferra.ru"
    const val TDNEWS_DOMAIN = "3dnews.ru"
    const val DOMAINS = "$IXBT_DOMAIN,$FERRA_DOMAIN,$TDNEWS_DOMAIN"
    const val LANG = "ru"
    const val SORT_PUBLISHED = "publishedAt"
    const val PAGE_SIZE = 20
}