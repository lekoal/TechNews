package com.private_projects.technews.ui.ferra

import com.private_projects.technews.data.vkdata.VkNewsPagerAdapter
import com.private_projects.technews.databinding.FragmentFerraNewsBinding
import com.private_projects.technews.ui.CommonContract
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class FerraNewsFragment :
    CommonContract.CommonFragment<FragmentFerraNewsBinding>(FragmentFerraNewsBinding::inflate) {
    override val scope by lazy {
        getKoin().getOrCreateScope<FerraNewsFragment>(SCOPE_ID)
    }
    override val viewModel: CommonContract.CommonViewModel by lazy {
        scope.get(named("ferra_news_view_model"))
    }
    override val adapter: VkNewsPagerAdapter by lazy {
        get(named("news_adapter"))
    }

    companion object {
        private const val SCOPE_ID = "ferra_news_scope"
    }

    override fun initRV() {
        binding.newsRv.layoutManager = layoutManager
        binding.newsRv.adapter = adapter
    }
}