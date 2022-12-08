package com.private_projects.technews.data.detailsEntities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoBlockEntity(
    @PrimaryKey(autoGenerate = true)
    val videoBlockId: Long = 0,
    val ownerId: String,
    val position: Int,
    val url: String
)
