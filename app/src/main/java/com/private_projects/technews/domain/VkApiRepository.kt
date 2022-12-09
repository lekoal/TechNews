package com.private_projects.technews.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.private_projects.technews.data.vkdata.VkWallGetDTO

interface VkApiRepository {
    fun vkGet(): LiveData<PagingData<VkWallGetDTO.Response.Item>>
}