package com.private_projects.technews.domain

import com.private_projects.technews.data.vkdata.VkHelpData
import com.private_projects.technews.data.vkdata.VkWallGetDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VkApi {
    @GET("wall.get")
    fun vkGet(
        @Query("owner_id") ownerId: Int,
        @Query("count") count: Int = VkHelpData.PAGE_SIZE,
        @Query("offset") offset: Int,
        @Query("access_token") accessToken: String = VkHelpData.VK_API_KEY,
        @Query("v") version: String = VkHelpData.API_V,
    ): Response<VkWallGetDTO.Response>
}