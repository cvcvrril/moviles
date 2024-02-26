package com.example.prcticaexamen.data.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prcticaexamen.data.model.VideojuegoEntity
import com.example.prcticaexamen.data.sources.local.VideojuegosDao

@Database(entities = [VideojuegoEntity::class], version = 1, exportSchema = false)
abstract class VideojuegosRoomDatabase : RoomDatabase() {
    abstract fun videojuegosDao(): VideojuegosDao
}