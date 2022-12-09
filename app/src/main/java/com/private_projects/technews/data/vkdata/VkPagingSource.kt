package com.private_projects.technews.data.vkdata

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.private_projects.technews.domain.VkApi

class VkPagingSource(
    private val vkApi: VkApi,
    private val ownerId: Int
) : PagingSource<Int, VkWallGetDTO.Response.Item>() {
    override fun getRefreshKey(state: PagingState<Int, VkWallGetDTO.Response.Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, VkWallGetDTO.Response.Item> {
        return try {
            val position = params.key ?: 1
            val response = vkApi.vkGet(
                ownerId = ownerId,
                offset = position
            )
            LoadResult.Page(
                data = response.body()!!.items,
                prevKey = if (position == 1) null
                else position - 1,
                nextKey = position + 1
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }

}