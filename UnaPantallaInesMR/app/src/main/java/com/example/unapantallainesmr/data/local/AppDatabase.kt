package com.example.unapantallainesmr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unapantallainesmr.data.SerieDao
import com.example.unapantallainesmr.data.modelo.SerieEntity

@Database(entities = [SerieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serieDao() : SerieDao

}