package com.private_projects.technews.ui.allnews

import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.CommonContract

class AllNewsViewModel(
    repository: NewsApiRepository
) : CommonContract.CommonViewModel(repository) {
    override fun receiveData(dataList: List<String>) {

    }
}