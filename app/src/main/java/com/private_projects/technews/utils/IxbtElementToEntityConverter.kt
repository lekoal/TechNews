package com.private_projects.technews.utils

import com.private_projects.technews.data.detailsEntities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.nodes.Element

class IxbtElementToEntityConverter(
    private val newsId: String
) {
    private val textBlocks = mutableListOf<TextBlockEntity>()
    private val imageBlocks = mutableListOf<ImageBlockEntity>()
    private val videoBlocks = mutableListOf<VideoBlockEntity>()

    fun insertRawData(element: Element): Flow<NewsDetailsEntity> = flow {
        if (element.select("div.b-article").isNullOrEmpty()) {
//            emit(
//                convertUser(element.selectFirst("div.container")!!)
//            )
        } else {
            emit(
                convertOfficial(element.selectFirst("div.b-article")!!)
            )
        }
    }

    private fun convertOfficial(element: Element): NewsDetailsEntity {
        val tempFirstTitle =
            element.selectFirst("div.b-article__header")?.selectFirst("h1")?.text().toString()
        val tempSecondTitle =
            element.selectFirst("div.b-article__header")?.selectFirst("h4")?.text().toString()
        val headerBlock = HeaderBlockEntity(
            newsId = newsId,
            firstTitle = tempFirstTitle,
            secondTitle = tempSecondTitle
        )
        element.selectFirst("div.b-article__content").let { innerElement ->
            innerElement?.allElements?.forEachIndexed { index, block ->
                if (!block.select("p").isNullOrEmpty()) {
                    textBlocks.add(
                        TextBlockEntity(
                            ownerId = newsId,
                            position = index,
                            content = block.select("p").text().toString()
                        )
                    )
                }
                if (block.select("div")
                        .attr("class") == "image-center" ||
                    block.select("figure")
                        .attr("class") == "image-caption image-left"
                ) {
                    imageBlocks.add(
                        ImageBlockEntity(
                            ownerId = newsId,
                            position = index,
                            url = block.select("img").attr("src").toString()
                        )
                    )
                }
                if (!block.select("div").attr("data-oembed-url")
                        .isNullOrEmpty()
                ) {
                    videoBlocks.add(
                        VideoBlockEntity(
                            ownerId = newsId,
                            position = index,
                            url = block.select("div").attr("data-oembed-url")
                                .toString()
                        )
                    )
                }
            }
        }
        return NewsDetailsEntity(
            headerBlock,
            textBlocks,
            imageBlocks,
            videoBlocks
        )
    }

//    private fun convertUser(element: Element): NewsDetailsEntity {
//
//    }
}