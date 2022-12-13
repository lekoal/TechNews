package com.private_projects.technews.domain.ixbt

import com.private_projects.technews.data.vkdata.*
import com.private_projects.technews.data.vkdata.ixbt.VkIxbtDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VkIxbtApi {
    @GET("wall.get")
    fun getNews(
        @Query("owner_id") ownerId: String = VkHelpData.IXBT_ID,
        @Query("count") count: Int = VkHelpData.PAGE_SIZE,
        @Query("offset") page: Int,
        @Query("access_token") accessToken: String = VkHelpData.VK_API_KEY,
        @Query("v") version: String = VkHelpData.API_V
    ): Response<VkIxbtDTO>
}