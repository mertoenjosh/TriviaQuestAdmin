package com.mertoenjosh.questprovider.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertoenjosh.questprovider.data.database.models.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM tbl_questions")
    fun getAllQuestions(): PagingSource<Int, QuestionEntity>
    @Query("SELECT * FROM tbl_questions WHERE id = :id")
    suspend fun getQuestionById(id: String): QuestionEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuestions(questions: List<QuestionEntity>)
    @Query("DELETE FROM tbl_questions")
    suspend fun deleteAllQuestions()
}