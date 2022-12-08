package com.private_projects.technews.data.detailsEntities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageBlockEntity(
    @PrimaryKey(autoGenerate = true)
    val imageBlockId: Long = 0,
    val ownerId: String,
    val position: Int,
    val url: String
)
