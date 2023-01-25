package com.mertoenjosh.triviaquestadmin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mertoenjosh.triviaquestadmin.data.local.dao.QuestionDao
import com.mertoenjosh.triviaquestadmin.data.local.dao.QuestProviderRemoteKeysDao
import com.mertoenjosh.triviaquestadmin.data.models.TriviaQuestion
import com.mertoenjosh.triviaquestadmin.data.models.TriviaQuestionRemoteKeys
import com.mertoenjosh.triviaquestadmin.data.models.StringListConverters

@Database(entities = [TriviaQuestion::class, TriviaQuestionRemoteKeys::class], version = 1)
@TypeConverters(StringListConverters::class)
abstract class QuestionsDatabase: RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun remoteKeysDao(): QuestProviderRemoteKeysDao
}