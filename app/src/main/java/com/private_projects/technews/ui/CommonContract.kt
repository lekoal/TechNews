package com.private_projects.technews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.private_projects.technews.data.newsapi.NewsPagerAdapter
import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.main.MainActivity
import com.private_projects.technews.utils.ViewBindingFragment
import org.koin.core.scope.Scope

interface CommonContract {
    abstract class CommonViewModel(private val repository: NewsApiRepository) : ViewModel() {
        fun getNews() = repository.getNews().cachedIn(viewModelScope)
    }

    abstract class CommonFragment<T: ViewBinding>(
        inflateBinding: (
            inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
        ) -> T
    ) : ViewBindingFragment<T>(inflateBinding) {
        abstract val scope: Scope
        abstract val viewModel: CommonViewModel
        abstract val adapter: NewsPagerAdapter
        protected lateinit var layoutManager: LinearLayoutManager
        private lateinit var parentActivity: MainActivity

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            parentActivity = requireActivity() as MainActivity
            initRV()
            setData()
            onItemClick()
            isLoading()
        }
        abstract fun initRV()
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
        private fun isLoading() {
            adapter.addLoadStateListener { state ->
                parentActivity.setProgress(state.refresh is LoadState.Loading)
            }
        }
    }
}