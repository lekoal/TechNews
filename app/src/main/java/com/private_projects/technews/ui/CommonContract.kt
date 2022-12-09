package com.private_projects.technews.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.private_projects.technews.data.vkdata.VkNewsPagerAdapter
import com.private_projects.technews.domain.VkApiRepository
import com.private_projects.technews.ui.main.MainActivity
import com.private_projects.technews.utils.ViewBindingFragment
import org.koin.core.scope.Scope

interface CommonContract {
    abstract class CommonViewModel(private val repository: VkApiRepository) : ViewModel() {
        fun getNews() = repository.vkGet().cachedIn(viewModelScope)
        abstract fun receiveData(dataList: List<String>)
    }

    abstract class CommonFragment<T: ViewBinding>(
        inflateBinding: (
            inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
        ) -> T
    ) : ViewBindingFragment<T>(inflateBinding) {
        abstract val scope: Scope
        abstract val viewModel: CommonViewModel
        abstract val adapter: VkNewsPagerAdapter
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
            adapter.onItemClick = { dataList ->
                viewModel.receiveData(dataList)
            }
        }
        private fun isLoading() {
            adapter.addLoadStateListener { state ->
                parentActivity.setProgress(state.refresh is LoadState.Loading)
            }
        }
    }
}