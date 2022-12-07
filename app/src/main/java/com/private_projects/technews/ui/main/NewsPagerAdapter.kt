package com.private_projects.technews.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.private_projects.technews.R
import com.private_projects.technews.data.newsapi.NewsDTO
import com.private_projects.technews.databinding.AllNewsRvItemBinding
import java.text.SimpleDateFormat

class NewsPagerAdapter :
    PagingDataAdapter<NewsDTO.Article, NewsPagerAdapter.AllNewsViewHolder>(NewsListComparator) {
    var onItemClick: ((String) -> Unit)? = null

    object NewsListComparator : DiffUtil.ItemCallback<NewsDTO.Article>() {
        override fun areItemsTheSame(oldItem: NewsDTO.Article, newItem: NewsDTO.Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: NewsDTO.Article,
            newItem: NewsDTO.Article
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: AllNewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.title.text = newsItem?.title
        holder.author.text = newsItem?.author
        val formatter = SimpleDateFormat("yyyy-dd-MM'T'hh:mm:ss'Z'")
        val date = formatter.parse(newsItem!!.publishedAt)
        val setDateFormat = SimpleDateFormat("HH:mm dd.MM.yy")
        val setDate = setDateFormat.format(date!!)
        holder.dateTime.text = setDate.toString()
        Glide
            .with(holder.view)
            .load(newsItem.urlToImage)
            .override(200, 100)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(holder.image)
        holder.description.text = newsItem.description
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(newsItem.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AllNewsRvItemBinding.inflate(inflater, parent, false)
        return AllNewsViewHolder(binding)
    }

    inner class AllNewsViewHolder(binding: AllNewsRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val view = binding.root
        val title = binding.allNewsRvItemTitle
        val author = binding.allNewsRvItemAuthor
        val dateTime = binding.allNewsRvItemDate
        val image = binding.allNewsRvItemImage
        val description = binding.allNewsRvItemDescription
    }
}