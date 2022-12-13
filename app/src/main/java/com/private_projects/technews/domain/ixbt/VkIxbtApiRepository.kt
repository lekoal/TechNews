package com.private_projects.technews.domain.ixbt

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.private_projects.technews.data.vkdata.ixbt.VkIxbtDTO

interface VkIxbtApiRepository {
    fun getNews(): LiveData<PagingData<VkIxbtDTO.Response.Item>>
}