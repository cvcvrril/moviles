package com.example.flowroomsinesmr.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flowroomsinesmr.data.Converters
import com.example.flowroomsinesmr.data.dao.JugadorDao
import com.example.flowroomsinesmr.data.modelo.entity.JugadorEntity

@Database(entities = [JugadorEntity::class], version =1, exportSchema = true)
//@TypeConverters(Converters::class)
abstract class JugadorRoomDatabase : RoomDatabase() {

    abstract fun jugadorDao() : JugadorDao


    companion object{
        @Volatile
        private var INSTANCE: JugadorRoomDatabase? = null
        fun getDatabase(context: Context): JugadorRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JugadorRoomDatabase::class.java,
                    "item_database"
                )
                    .createFromAsset("database/jugadores.db")
                    .fallbackToDestructiveMigrationFrom(4)
                    .build()
                INSTANCE = instance
                instance

            }
        }

    }







}