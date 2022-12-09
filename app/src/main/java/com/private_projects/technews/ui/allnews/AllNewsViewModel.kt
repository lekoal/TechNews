package com.private_projects.technews.ui.allnews

import com.private_projects.technews.domain.VkApiRepository
import com.private_projects.technews.ui.CommonContract

class AllNewsViewModel(
    repository: VkApiRepository
) : CommonContract.CommonViewModel(repository) {
    override fun receiveData(dataList: List<String>) {

    }
}