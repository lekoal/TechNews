package com.private_projects.technews.ui.ixbt

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.private_projects.technews.data.vkdata.ixbt.VkIxbtDTO
import com.private_projects.technews.domain.ixbt.VkIxbtApi

class VkIxbtPagingSource(
    private val vkIxbtApi: VkIxbtApi
) : PagingSource<Int, VkIxbtDTO.Response.Item>() {
    override fun getRefreshKey(state: PagingState<Int, VkIxbtDTO.Response.Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VkIxbtDTO.Response.Item> {
        return try {
            val position = params.key ?: 1
            val response = vkIxbtApi.getNews(
                page = position
            )
            println(response)
            LoadResult.Page(
                data = response.body()!!.response.items,
                prevKey = if (position == 1) null
                else position - 1,
                nextKey = position + 1
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}