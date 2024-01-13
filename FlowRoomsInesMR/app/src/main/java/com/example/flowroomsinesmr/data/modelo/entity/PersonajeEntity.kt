package com.example.flowroomsinesmr.data.modelo.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flowroomsinesmr.domain.modelo.Personaje


@Entity(tableName = "personajes")
data class PersonajeEntity(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "idVideojuego")
    val idVideojuego: Int

)

fun PersonajeEntity.toPersonaje() : Personaje = Personaje(id, nombre, descripcion, idVideojuego)
fun Personaje.toPersonajeEntity() : PersonajeEntity = PersonajeEntity(id, nombre, descripcion, idVideojuego)




