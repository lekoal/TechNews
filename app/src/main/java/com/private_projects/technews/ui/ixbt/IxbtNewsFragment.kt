package com.private_projects.technews.ui.ixbt

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.private_projects.technews.databinding.FragmentIxbtNewsBinding
import com.private_projects.technews.utils.ViewBindingFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class IxbtNewsFragment :
    ViewBindingFragment<FragmentIxbtNewsBinding>(FragmentIxbtNewsBinding::inflate) {
    private val scope by lazy {
        getKoin().getOrCreateScope<IxbtNewsFragment>(SCOPE_ID)
    }

    private val adapter: VkIxbtPagerAdapter by lazy {
        scope.get(named("ixbt_adapter"))
    }

    private val viewModel: IxbtNewsViewModel by lazy {
        scope.get(named("ixbt_view_model"))
    }

    companion object {
        private const val SCOPE_ID = "ixbt_news_scope_id"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ixbtNewsRv.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        binding.ixbtNewsRv.adapter = adapter

        lifecycleScope.launch {
            viewModel.getNews().observe(viewLifecycleOwner) {
                adapter.submitData(lifecycle, it)
            }
        }
    }
}