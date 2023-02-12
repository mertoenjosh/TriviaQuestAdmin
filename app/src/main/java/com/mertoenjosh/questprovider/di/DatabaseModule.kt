package com.mertoenjosh.questprovider.di

import android.content.Context
import androidx.room.Room
import com.mertoenjosh.questprovider.data.database.QuestionsDatabase
import com.mertoenjosh.questprovider.util.Constants
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
    fun provideDatabase(@ApplicationContext context: Context): QuestionsDatabase {
        return Room.databaseBuilder(
            context,
            QuestionsDatabase::class.java,
            Constants.QUEST_PROVIDER_DATABASE
        ).build()
    }
}