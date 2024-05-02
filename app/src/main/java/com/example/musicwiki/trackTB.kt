package com.example.musicwiki

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "trackTable")
data class trackTB (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "trackName")
    var trackName: String,
    @ColumnInfo(name = "artistName")
    var artistName: String,
    @ColumnInfo(name = "albumName")
    var albumName: String,
    @ColumnInfo(name = "duration")
    var durationTr: String,
    @ColumnInfo(name = "textTr")
    var textTr: String,
    @ColumnInfo(name = "imageLink")
    var imageLink: Int,
    @ColumnInfo(name = "audioLink")
    var audioLink: Int,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean
)
