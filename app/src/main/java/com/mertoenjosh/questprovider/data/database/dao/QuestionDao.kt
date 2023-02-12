package com.mertoenjosh.questprovider.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertoenjosh.questprovider.data.models.TriviaQuestion

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions_table")
    fun getAllQuestions(): PagingSource<Int, TriviaQuestion>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuestions(questions: List<TriviaQuestion>)

    @Query("DELETE FROM questions_table")
    suspend fun deleteAllQuestions()
}