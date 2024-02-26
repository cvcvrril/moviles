package com.example.prcticaexamen.data.sources.local

import androidx.room.Dao
import androidx.room.Query
import com.example.prcticaexamen.data.model.VideojuegoEntity

@Dao
interface VideojuegosDao {

    @Query("SELECT * from videojuegos")
    suspend fun getAllVideojuegosRoom() : List<VideojuegoEntity>?

}