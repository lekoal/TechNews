package com.private_projects.technews.data.vkdata.ixbt

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.private_projects.technews.data.vkdata.VkHelpData
import com.private_projects.technews.ui.ixbt.VkIxbtPagingSource
import com.private_projects.technews.domain.ixbt.VkIxbtApi
import com.private_projects.technews.domain.ixbt.VkIxbtApiRepository

class VkIxbtApiRepositoryImpl(
    private val vkApi: VkIxbtApi
) : VkIxbtApiRepository {

    override fun getNews(): LiveData<PagingData<VkIxbtDTO.Response.Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = VkHelpData.PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = 5
            ),
            pagingSourceFactory = { VkIxbtPagingSource(vkApi) },
            initialKey = 1
        ).liveData
    }
}