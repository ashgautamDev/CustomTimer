package com.ashish.custometimer.di

import android.app.Application
import androidx.room.Room
import com.ashish.custometimer.R
import com.ashish.custometimer.persistance.AppDatabase
import com.ashish.custometimer.persistance.CustomeTaskDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "CustomeTimer.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePosterDao(appDatabase: AppDatabase): CustomeTaskDao {
        return appDatabase.customeTaskDao()
    }
}