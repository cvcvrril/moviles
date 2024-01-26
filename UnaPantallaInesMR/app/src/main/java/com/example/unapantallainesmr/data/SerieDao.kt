package com.example.unapantallainesmr.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.unapantallainesmr.data.modelo.SerieEntity
import com.example.unapantallainesmr.domain.modelo.Serie

@Dao
interface SerieDao {
    @Query("select * from series")
    suspend fun getAll(): List<SerieEntity>

    @Query("select * from series where id = :id")
    suspend fun get(id: Int): SerieEntity
    @Insert
    suspend fun insert(serie: Serie)
    @Delete
    suspend fun delete(serie: Serie)
    @Update
    suspend fun update(serie: Serie)

}