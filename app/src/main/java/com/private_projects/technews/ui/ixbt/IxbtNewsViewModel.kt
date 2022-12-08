package com.private_projects.technews.ui.ixbt

import android.util.Log
import com.private_projects.technews.domain.ElementReceiver
import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.CommonContract
import com.private_projects.technews.utils.IxbtElementToEntityConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.nodes.Element

class IxbtNewsViewModel(
    repository: NewsApiRepository,
    private val elementReceiver: ElementReceiver
) : CommonContract.CommonViewModel(repository) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var newsId = ""

    override fun receiveData(dataList: List<String>) {
        newsId = dataList[1] + dataList[2] + dataList[3]
        coroutineScope.launch {
            elementReceiver.get(dataList[0]).collect { element ->
                convertData(element)
            }
        }
    }

    private fun convertData(element: Element) {
        val converter = IxbtElementToEntityConverter(newsId)
        coroutineScope.launch {
            converter.insertRawData(element).collect {
                Log.i("MY", it.toString())
            }
        }
    }
}