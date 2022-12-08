package com.private_projects.technews.ui.ixbt

import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.CommonContract

class IxbtNewsViewModel(
    repository: NewsApiRepository
) : CommonContract.CommonViewModel(repository)