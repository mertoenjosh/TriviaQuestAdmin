package com.mertoenjosh.questprovider.data.di

import com.mertoenjosh.questprovider.data.database.QpDatabase
import com.mertoenjosh.questprovider.data.database.dao.AuthDao
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
    fun providesAuthDao(qpDatabase: QpDatabase): AuthDao = qpDatabase.authDao()
}