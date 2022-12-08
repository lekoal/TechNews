package com.private_projects.technews.domain

import kotlinx.coroutines.flow.Flow
import org.jsoup.Connection.Response
import org.jsoup.nodes.Element

interface ElementReceiver {
    fun get(url: String): Flow<Element>
    fun response(): Response?
}