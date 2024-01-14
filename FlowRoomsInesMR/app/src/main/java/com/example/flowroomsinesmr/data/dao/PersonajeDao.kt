package com.example.flowroomsinesmr.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flowroomsinesmr.data.modelo.entity.PersonajeEntity

@Dao
interface PersonajeDao {

    @Query("select * from personajes")
    fun getAll() : List<PersonajeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(personajes: List<PersonajeEntity>)

    @Delete
    fun deleteAll(videojuegos: List<PersonajeEntity>)


}