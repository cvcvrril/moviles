package com.example.unapantallainesmr.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unapantallainesmr.data.modelo.SerieEntity


@Database(entities = [SerieEntity::class], version = 1, exportSchema = true)
abstract class SerieRoomDatabase : RoomDatabase(){
    abstract fun serieDao(): SerieDao

    companion object{
        @Volatile
        private var INSTANCE: SerieRoomDatabase? = null

        fun getDatabase(context: Context): SerieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SerieRoomDatabase::class.java,
                    "item_database"
                )
                    .createFromAsset("database/serie.db")
                    .fallbackToDestructiveMigrationFrom(4)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }

        }


    }


}