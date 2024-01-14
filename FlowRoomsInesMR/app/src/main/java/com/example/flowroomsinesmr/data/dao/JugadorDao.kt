package com.example.flowroomsinesmr.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flowroomsinesmr.data.modelo.entity.JugadorEntity
import com.example.flowroomsinesmr.data.modelo.entity.PersonajeEntity

@Dao
interface JugadorDao {
    @Query("select * from jugadores")
    fun getAll() : List<JugadorEntity>



}