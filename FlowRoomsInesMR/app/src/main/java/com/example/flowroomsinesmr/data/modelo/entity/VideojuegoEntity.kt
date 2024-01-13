package com.example.flowroomsinesmr.data.modelo.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flowroomsinesmr.domain.modelo.Videojuego

@Entity(tableName = "videojuegos")
data class VideojuegoEntity (
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "titulo")
    val titulo: String,
    @ColumnInfo(name = "descripcion")
    val descripcion: String
)

fun VideojuegoEntity.toVideojuego() : Videojuego = Videojuego(id, titulo, descripcion)
fun Videojuego.toVideojuegoEntity() : VideojuegoEntity = VideojuegoEntity(id, titulo, descripcion)