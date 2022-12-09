package com.private_projects.technews.data.vkdata

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.private_projects.technews.R
import com.private_projects.technews.databinding.NewsRvItemBinding
import com.private_projects.technews.utils.TimestampConverter

class VkNewsPagerAdapter :
    PagingDataAdapter<VkWallGetDTO.Response.Item, VkNewsPagerAdapter.NewsViewHolder>(
        NewsListComparator
    ) {
    var onItemClick: ((List<String>) -> Unit)? = null

    object NewsListComparator : DiffUtil.ItemCallback<VkWallGetDTO.Response.Item>() {
        override fun areItemsTheSame(
            oldItem: VkWallGetDTO.Response.Item, newItem: VkWallGetDTO.Response.Item
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: VkWallGetDTO.Response.Item,
            newItem: VkWallGetDTO.Response.Item
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        val completeText = newsItem?.text
        val splitText = completeText?.split("\\n\\n", limit = 2)
        var newsUrl = ""
        holder.title.text = splitText?.get(0).toString()
        when (newsItem?.ownerId) {
            VkHelpData.IXBT_ID -> {
                holder.author.text = "ixbt.com"
                newsItem.attachments.forEach { attachment ->
                    when (attachment.type) {
                        "link" -> newsUrl = attachment.link?.url.toString()
                        "photo" -> {
                            attachment.link?.photo?.sizes?.forEach { size ->
                                loadPhoto(size, holder)
                            }
                        }
                    }
                }
            }
            VkHelpData.FERRA_ID -> {
                holder.author.text = "ferra.ru"
                newsUrl = splitText?.get(1)?.substringAfterLast('.').toString()
                newsItem.attachments.forEach { attachment ->
                    when (attachment.type) {
                        "photo" -> {
                            attachment.link?.photo?.sizes?.forEach { size ->
                                loadPhoto(size, holder)
                            }
                        }
                    }
                }
            }
            VkHelpData.TDNEWS_ID -> {
                holder.author.text = "3dnews.ru"
                newsUrl = newsItem.attachments[0].link?.url.toString()
                newsItem.attachments[0].link?.photo?.sizes?.forEach { size ->
                    loadPhoto(size, holder)
                }
            }
            else -> holder.author.text = ""
        }
        val timestampConverter = TimestampConverter()
        holder.dateTime.text = timestampConverter.convert(newsItem?.date ?: 0)

        holder.description.text = splitText?.get(1).toString()
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(
                listOf(
                    newsUrl,
                    newsItem?.ownerId.toString(),
                    newsItem?.id.toString()
                )
            )
        }
    }

    private fun loadPhoto(
        size: VkWallGetDTO.Response.Item.Attachment.Link.Photo.Size,
        holder: NewsViewHolder
    ) {
        if (size.type == "q" ||
            size.type == "p" ||
            size.type == "r"
        ) {
            Glide
                .with(holder.view)
                .load(size.url)
                .override(200, 100)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(holder.image)
            return
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsRvItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    inner class NewsViewHolder(binding: NewsRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val view = binding.root
        val title = binding.rvItemTitle
        val author = binding.rvItemAuthor
        val dateTime = binding.rvItemDate
        val image = binding.rvItemImage
        val description = binding.rvItemDescription
    }
}