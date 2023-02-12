package com.mertoenjosh.questprovider.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mertoenjosh.questprovider.data.database.dao.QuestProviderRemoteKeysDao
import com.mertoenjosh.questprovider.data.database.dao.QuestionDao
import com.mertoenjosh.questprovider.data.models.TriviaQuestion
import com.mertoenjosh.questprovider.data.models.TriviaQuestionRemoteKeys
import com.mertoenjosh.questprovider.data.models.StringListConverters

@Database(entities = [TriviaQuestion::class, TriviaQuestionRemoteKeys::class], version = 1)
@TypeConverters(StringListConverters::class)
abstract class QuestionsDatabase: RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun remoteKeysDao(): QuestProviderRemoteKeysDao
}