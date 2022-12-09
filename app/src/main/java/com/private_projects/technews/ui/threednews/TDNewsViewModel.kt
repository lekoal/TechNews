package com.private_projects.technews.ui.threednews

import com.private_projects.technews.domain.VkApiRepository
import com.private_projects.technews.ui.CommonContract

class TDNewsViewModel(
    repository: VkApiRepository
) : CommonContract.CommonViewModel(repository) {
    override fun receiveData(dataList: List<String>) {

    }
}