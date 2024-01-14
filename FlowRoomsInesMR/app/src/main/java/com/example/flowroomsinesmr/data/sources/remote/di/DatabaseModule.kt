package com.example.flowroomsinesmr.data.sources.remote.di

import android.content.Context
import androidx.room.Room
import com.example.flowroomsinesmr.data.dao.JugadorDao
import com.example.flowroomsinesmr.data.dao.VideojuegoDao
import com.example.flowroomsinesmr.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideJugadorDao(appDatabase: AppDatabase): JugadorDao {
        return appDatabase.jugadorDao()
    }

    @Provides
    fun provideVideojuegorDao(appDatabase: AppDatabase): VideojuegoDao {
        return appDatabase.videojuegoDao()
    }

}