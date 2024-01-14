package com.example.flowroomsinesmr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flowroomsinesmr.data.dao.JugadorDao
import com.example.flowroomsinesmr.data.dao.PersonajeDao
import com.example.flowroomsinesmr.data.dao.VideojuegoDao
import com.example.flowroomsinesmr.data.modelo.entity.JugadorEntity
import com.example.flowroomsinesmr.data.modelo.entity.VideojuegoEntity

@Database(entities = [VideojuegoEntity::class, JugadorEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videojuegoDao() : VideojuegoDao
    //abstract fun personajeDao() : PersonajeDao
    abstract fun jugadorDao() : JugadorDao

}