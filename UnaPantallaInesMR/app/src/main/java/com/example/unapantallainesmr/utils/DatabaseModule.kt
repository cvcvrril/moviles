package com.example.unapantallainesmr.utils

import android.content.Context
import androidx.room.Room
import com.example.unapantallainesmr.data.SerieDao
import com.example.unapantallainesmr.data.local.SerieRoomDatabase
import com.example.unapantallainesmr.data.local.AppDatabase
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
            "series.db"
        ).createFromAsset("database/series.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideSerieDao(appDatabase: AppDatabase): SerieDao {
        return appDatabase.serieDao()
    }

}