package com.example.musicwiki

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "artistTable")
data class artistTB(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "artistName")
    var artistName: String,
    @ColumnInfo(name = "history")
    var history: String,
    @ColumnInfo(name = "imageLink")
    var imageID: Int
)
