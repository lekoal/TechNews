package com.private_projects.technews.data.detailsEntities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "texts")
data class TextBlockEntity(
    @PrimaryKey(autoGenerate = true)
    val textBlockId: Long = 0,
    val ownerId: String,
    val position: Int,
    val content: String
)
