package com.private_projects.technews.ui.allnews

import android.os.Bundle
import android.view.View
import com.private_projects.technews.databinding.FragmentAllNewsBinding
import com.private_projects.technews.ui.main.MainActivity
import com.private_projects.technews.utils.ViewBindingFragment
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class AllNewsFragment :
    ViewBindingFragment<FragmentAllNewsBinding>(FragmentAllNewsBinding::inflate) {
    private val scope by lazy {
        getKoin().getOrCreateScope<AllNewsFragment>(SCOPE_ID)
    }
    private val viewModel: AllNewsViewModel by lazy {
        scope.get(named("all_news_view_model"))
    }

    companion object {
        private const val SCOPE_ID = "all_news_scope"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.result.observe(viewLifecycleOwner) {
            it.articles.forEach { article ->

            }
        }
        viewModel.getNews()
        val parentActivity: MainActivity = requireActivity() as MainActivity
        parentActivity.setProgress(true)
    }
}