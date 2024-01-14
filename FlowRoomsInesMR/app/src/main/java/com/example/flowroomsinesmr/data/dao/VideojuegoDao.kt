package com.example.flowroomsinesmr.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flowroomsinesmr.data.modelo.entity.VideojuegoEntity
import com.example.flowroomsinesmr.domain.modelo.Videojuego
import dagger.Provides

@Dao
interface VideojuegoDao {

    @Query("select * from videojuegos")
    fun getAll() : List<VideojuegoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videojuegos: List<VideojuegoEntity>)
    @Delete
    fun deleteAll(videojuegos: List<VideojuegoEntity>)

}