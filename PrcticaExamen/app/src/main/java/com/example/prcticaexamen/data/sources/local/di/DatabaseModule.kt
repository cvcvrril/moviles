package com.example.prcticaexamen.data.sources.local.di

import android.content.Context
import androidx.room.Room
import com.example.prcticaexamen.data.common.VideojuegosRoomDatabase
import com.example.prcticaexamen.data.sources.local.VideojuegosDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): VideojuegosRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            VideojuegosRoomDatabase::class.java,
            "app.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideVideojuegosDao(appDatabase: VideojuegosRoomDatabase): VideojuegosDao {
        return appDatabase.videojuegosDao()
    }
}