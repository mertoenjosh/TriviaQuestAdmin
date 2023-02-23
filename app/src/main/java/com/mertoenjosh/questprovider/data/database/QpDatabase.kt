package com.mertoenjosh.questprovider.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mertoenjosh.questprovider.data.database.dao.QuestProviderRemoteKeysDao
import com.mertoenjosh.questprovider.data.database.dao.QuestionDao
import com.mertoenjosh.questprovider.data.database.dao.UserDao
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import com.mertoenjosh.questprovider.data.database.models.QuestionRemoteKeysEntity
import com.mertoenjosh.questprovider.data.database.models.StringListConverters
import com.mertoenjosh.questprovider.data.database.models.UserEntity

@Database(entities = [UserEntity::class ,QuestionEntity::class, QuestionRemoteKeysEntity::class], version = 1)
@TypeConverters(StringListConverters::class)
abstract class QpDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun questionDao(): QuestionDao
    abstract fun remoteKeysDao(): QuestProviderRemoteKeysDao
}