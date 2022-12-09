package com.private_projects.technews.ui.ferra

import com.private_projects.technews.domain.VkApiRepository
import com.private_projects.technews.ui.CommonContract

class FerraNewsViewModel(
    repository: VkApiRepository
) : CommonContract.CommonViewModel(repository) {
    override fun receiveData(dataList: List<String>) {

    }
}