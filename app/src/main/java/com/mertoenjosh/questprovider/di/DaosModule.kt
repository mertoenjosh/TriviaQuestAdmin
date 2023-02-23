package com.mertoenjosh.questprovider.di

import com.mertoenjosh.questprovider.data.database.QpDatabase
import com.mertoenjosh.questprovider.data.database.dao.QuestionDao
import com.mertoenjosh.questprovider.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaosModule {
    @Singleton
    @Provides
    fun providesUserDao(qpDatabase: QpDatabase): UserDao = qpDatabase.userDao()

    @Singleton
    @Provides
    fun providesQuestionDao(qpDatabase: QpDatabase): QuestionDao = qpDatabase.questionDao()
}