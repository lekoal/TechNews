package com.private_projects.technews.ui.ixbt

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.private_projects.technews.data.vkdata.ixbt.VkIxbtDTO
import com.private_projects.technews.domain.ixbt.VkIxbtApiRepository

class IxbtNewsViewModel(
    private val repository: VkIxbtApiRepository
) : ViewModel() {
    fun getNews(): LiveData<PagingData<VkIxbtDTO.Response.Item>> {
        return repository.getNews()
    }

    fun receiveData(dataList: List<String>) {

    }
}