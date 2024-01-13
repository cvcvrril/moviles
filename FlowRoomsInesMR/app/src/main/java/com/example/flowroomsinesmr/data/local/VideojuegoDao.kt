package com.example.flowroomsinesmr.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flowroomsinesmr.data.modelo.entity.VideojuegoEntity

@Dao
interface VideojuegoDao {

    @Query("select * from videojuegos")
    fun getAll() : List<VideojuegoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videojuegos: List<VideojuegoEntity>)
    @Delete
    fun deleteAll(videojuegos: List<VideojuegoEntity>)

}