package com.mertoenjosh.questprovider.data.di

import android.content.Context
import androidx.room.Room
import com.mertoenjosh.questprovider.data.database.QpDatabase
import com.mertoenjosh.questprovider.data.util.Constants.QUEST_PROVIDER_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): QpDatabase {
        return Room.databaseBuilder(
            context,
            QpDatabase::class.java,
            QUEST_PROVIDER_DATABASE
        ).build()
    }
}