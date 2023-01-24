package com.mertoenjosh.triviaquestadmin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertoenjosh.triviaquestadmin.data.db.dao.QuestionDao
import com.mertoenjosh.triviaquestadmin.data.db.dao.QuestProviderRemoteKeysDao
import com.mertoenjosh.triviaquestadmin.data.models.Question
import com.mertoenjosh.triviaquestadmin.data.models.QuestProviderRemoteKeys

@Database(entities = [Question::class, QuestProviderRemoteKeys::class], version = 1)
abstract class QuestProviderDatabase: RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun remoteKeysDao(): QuestProviderRemoteKeysDao
}