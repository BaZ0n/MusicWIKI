package com.example.musicwiki
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    fun insertTrack(track: trackTB)
    @Query("SELECT * FROM trackTable")
    fun getAllItem(): Flow<List<trackTB>>
    @Query("SELECT COUNT(*) FROM trackTable")
    fun getRowCount(): Int
    @Query("DELETE FROM trackTable")
    fun deleteAll(): Unit
    @Query("SELECT * FROM trackTable WHERE id = :trackID")
    fun idSearch(trackID: Int?): Flow<List<trackTB>>
    @Query("UPDATE trackTable SET favorite = :favoriteValue WHERE id = :trackID")
    fun favoriteUpdate(trackID: Int?, favoriteValue: Boolean): Unit
    @Query("SELECT * FROM trackTable WHERE trackName LIKE '%' || :searchWord || '%' OR artistName LIKE '%' || :searchWord || '%'")
    fun getAllWhere(searchWord: String?): Flow<List<trackTB>>
    @Query("SELECT * FROM trackTable WHERE trackName = :searchWord OR artistName = :searchWord")
    fun getSong(searchWord: String?): Flow<List<trackTB>>
    @Query("SELECT * FROM trackTable WHERE artistName = :artName")
    fun songsFromArtist(artName: String?): Flow<List<trackTB>>

}