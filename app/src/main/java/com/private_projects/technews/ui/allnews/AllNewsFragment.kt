package com.private_projects.technews.ui.allnews

import com.private_projects.technews.data.newsapi.NewsPagerAdapter
import com.private_projects.technews.databinding.FragmentAllNewsBinding
import com.private_projects.technews.ui.CommonContract
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class AllNewsFragment :
    CommonContract.CommonFragment<FragmentAllNewsBinding>(FragmentAllNewsBinding::inflate) {
    override val scope by lazy {
        getKoin().getOrCreateScope<AllNewsFragment>(SCOPE_ID)
    }
    override val viewModel: CommonContract.CommonViewModel by lazy {
        scope.get(named("all_news_view_model"))
    }
    override val adapter: NewsPagerAdapter by lazy {
        get(named("news_adapter"))
    }

    companion object {
        private const val SCOPE_ID = "all_news_scope"
    }

    override fun initRV() {
        binding.allNewsRv.layoutManager = layoutManager
        binding.allNewsRv.adapter = adapter
    }
}