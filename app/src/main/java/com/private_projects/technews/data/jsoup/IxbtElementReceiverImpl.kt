package com.private_projects.technews.data.jsoup

import com.private_projects.technews.domain.ElementReceiver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Connection
import org.jsoup.Connection.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException

class IxbtElementReceiverImpl : ElementReceiver {
    private var doc: Document? = null
    private var element: Element? = null
    private var response: Response? = null

    override fun get(url: String): Flow<Element> = flow {
        try {
            val connection: Connection = Jsoup.connect(url)
            connection.userAgent("Chrome/107.0.5304.88 Safari/537.36")
            connection.referrer("http://www.google.com")
            connection.method(Connection.Method.GET)
            response = connection.execute()
            doc = connection.url(url).get()
            doc.let { document ->
                element = document?.selectFirst("body")
                element?.let {
                    emit(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun response(): Response? {
        return response
    }

}