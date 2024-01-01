package com.example.flowroomsinesmr.data.modelo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "videojuegos")
data class VideojuegoEntity (
    @ColumnInfo(name = "id")
    val id: Int
)