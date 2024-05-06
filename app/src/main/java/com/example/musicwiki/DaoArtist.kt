package com.example.musicwiki

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoArtist {
    @Insert
    fun insertArtist(artist: artistTB)
    @Query("SELECT * FROM artistTable")
    fun getAllItem(): Flow<List<artistTB>>
    @Query("SELECT COUNT(*) FROM artistTable")
    fun getRowCount(): Int
    @Query("DELETE FROM artistTable")
    fun deleteAll(): Unit
    @Query("SELECT * FROM artistTable WHERE id = :artistID")
    fun idSearch(artistID: Int?): Flow<List<artistTB>>
}