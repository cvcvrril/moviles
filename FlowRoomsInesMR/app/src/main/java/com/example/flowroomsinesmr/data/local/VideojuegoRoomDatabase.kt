package com.example.flowroomsinesmr.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flowroomsinesmr.data.dao.VideojuegoDao
import com.example.flowroomsinesmr.data.modelo.entity.JugadorEntity
import com.example.flowroomsinesmr.data.modelo.entity.VideojuegoEntity


@Database(entities = [VideojuegoEntity::class], version =1, exportSchema = true)
abstract class VideojuegoRoomDatabase : RoomDatabase() {

    abstract fun videojuegoDao(): VideojuegoDao

    companion object{
        @Volatile
        private var INSTANCE: VideojuegoRoomDatabase? = null
        fun getDatabase(context: Context): VideojuegoRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideojuegoRoomDatabase::class.java,
                    "item_database"
                )
                    .createFromAsset("database/videojuegos.db")
                    .fallbackToDestructiveMigrationFrom(4)
                    .build()
                INSTANCE = instance
                instance

            }
        }
    }


}