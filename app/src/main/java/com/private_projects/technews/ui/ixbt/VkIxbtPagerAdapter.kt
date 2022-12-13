package com.private_projects.technews.ui.ixbt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.private_projects.technews.data.vkdata.ixbt.VkIxbtDTO
import com.private_projects.technews.databinding.NewsRvItemBinding

class VkIxbtPagerAdapter :
    PagingDataAdapter<VkIxbtDTO.Response.Item, VkIxbtPagerAdapter.ViewHolder>(NewsListComparator) {
    var onItemClick: ((List<String>) -> Unit)? = null

    object NewsListComparator : DiffUtil.ItemCallback<VkIxbtDTO.Response.Item>() {
        override fun areItemsTheSame(
            oldItem: VkIxbtDTO.Response.Item,
            newItem: VkIxbtDTO.Response.Item
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: VkIxbtDTO.Response.Item,
            newItem: VkIxbtDTO.Response.Item
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newItem = getItem(position)
        println(newItem?.text)
        println("onBind viewHolder")
        holder.title.text = newItem?.text.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        println("onCreate viewHolder")
        val binding = NewsRvItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: NewsRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.rvItemTitle
    }
}