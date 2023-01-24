package com.mertoenjosh.triviaquestadmin.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertoenjosh.triviaquestadmin.data.models.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions_table")
    fun getAllQuestions(): PagingSource<Int, Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuestions(questions: List<Question>)

    @Query("DELETE FROM questions_table")
    suspend fun deleteAllQuestions()
}