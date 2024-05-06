package com.example.musicwiki

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [trackTB::class, artistTB::class], version = 3)
abstract class MainDB : RoomDatabase() {
    abstract fun getDao(): Dao
    abstract fun getDaoArtist(): DaoArtist
    companion object {
        fun getDB(context: Context): MainDB{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "music.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}