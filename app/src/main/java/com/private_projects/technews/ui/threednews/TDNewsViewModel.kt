package com.private_projects.technews.ui.threednews

import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.CommonContract

class TDNewsViewModel(
    repository: NewsApiRepository
) : CommonContract.CommonViewModel(repository) {
    override fun receiveData(dataList: List<String>) {

    }
}