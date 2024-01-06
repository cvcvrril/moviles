package com.example.flowroomsinesmr.data.sources.remote.di

import android.content.Context
import androidx.room.Room
import com.example.flowroomsinesmr.data.local.AppDatabase
import com.example.flowroomsinesmr.data.local.VideojuegoDao
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
    fun provideMovieDao(appDatabase: AppDatabase): VideojuegoDao {
        return appDatabase.videojuegoDao()
    }
}