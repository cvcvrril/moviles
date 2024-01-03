package com.example.flowroomsinesmr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flowroomsinesmr.data.modelo.entity.VideojuegoEntity

@Database(entities = [VideojuegoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videojuegoDao() : VideojuegoDao

}