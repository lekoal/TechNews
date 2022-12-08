package com.private_projects.technews.ui.ferra

import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.CommonContract

class FerraNewsViewModel(
    repository: NewsApiRepository
) : CommonContract.CommonViewModel(repository)