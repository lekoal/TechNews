package com.private_projects.technews.ui.allnews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.private_projects.technews.databinding.FragmentAllNewsBinding
import com.private_projects.technews.ui.main.NewsPagerAdapter
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
    private val adapter: NewsPagerAdapter by lazy {
        scope.get(named("all_news_adapter"))
    }

    private lateinit var layoutManager: LinearLayoutManager

    companion object {
        private const val SCOPE_ID = "all_news_scope"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
//        val parentActivity: MainActivity = requireActivity() as MainActivity
//        parentActivity.setProgress(true)
        initRV()
        setData()
        onItemClick()
    }

    private fun initRV() {
        binding.allNewsRv.layoutManager = layoutManager
        binding.allNewsRv.adapter = adapter
    }

    private fun setData() {
        viewModel.getNews().observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun onItemClick() {
        adapter.onItemClick = { url ->
            Toast.makeText(requireContext(), url, Toast.LENGTH_SHORT).show()
        }
    }
}