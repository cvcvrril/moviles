package com.example.unapantallainesmr.data

import androidx.room.Dao
import androidx.room.Query
import com.example.unapantallainesmr.data.modelo.SerieEntity

@Dao
interface SerieDao {
    @Query("select * from series")
    suspend fun getAll(): List<SerieEntity>

    @Query("select * from series where id = :id")
    suspend fun get(id: Int): SerieEntity

}