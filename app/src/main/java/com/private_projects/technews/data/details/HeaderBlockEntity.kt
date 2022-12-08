package com.private_projects.technews.data.details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class HeaderBlockEntity(
    @PrimaryKey
    val newsId: String,
    val firstTitle: String,
    val secondTitle: String?
)
