package com.private_projects.technews.ui.threednews

import com.private_projects.technews.data.vkdata.VkNewsPagerAdapter
import com.private_projects.technews.databinding.FragmentTDNewsBinding
import com.private_projects.technews.ui.CommonContract
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class TDNewsFragment :
    CommonContract.CommonFragment<FragmentTDNewsBinding>(FragmentTDNewsBinding::inflate) {
    override val scope by lazy {
        getKoin().getOrCreateScope<TDNewsFragment>(SCOPE_ID)
    }
    override val viewModel: CommonContract.CommonViewModel by lazy {
        scope.get(named("tdnews_view_model"))
    }
    override val adapter: VkNewsPagerAdapter by lazy {
        get(named("news_adapter"))
    }

    companion object {
        private const val SCOPE_ID = "tdnews_scope"
    }

    override fun initRV() {
        binding.newsRv.layoutManager = layoutManager
        binding.newsRv.adapter = adapter
    }
}