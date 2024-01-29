package com.example.unapantallainesmr.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class SerieEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "titulo")
    val titulo: String,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
)