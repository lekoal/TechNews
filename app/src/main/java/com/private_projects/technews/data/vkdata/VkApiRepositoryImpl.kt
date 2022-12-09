package com.private_projects.technews.data.vkdata

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.private_projects.technews.domain.VkApi
import com.private_projects.technews.domain.VkApiRepository

class VkApiRepositoryImpl(
    private val vkApi: VkApi,
    private val ownerId: Int
) : VkApiRepository {
    override fun vkGet(): LiveData<PagingData<VkWallGetDTO.Response.Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = VkHelpData.PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = 5
            ),
            pagingSourceFactory = { VkPagingSource(vkApi, ownerId) },
            initialKey = 1
        ).liveData
    }

}