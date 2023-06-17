package com.mertoenjosh.questprovider.data.database.models

import androidx.room.*
import com.mertoenjosh.questprovider.data.util.Constants.QUESTIONS_TABLE

@Entity(tableName = QUESTIONS_TABLE)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo
    val question: String,
    @ColumnInfo(name = "correct_answer")
    val correctAnswer: String,
    @ColumnInfo
    @TypeConverters(StringListConverters::class)
    val choices: List<String>,
    @ColumnInfo
    val difficulty: String,
    @ColumnInfo
    val category: String,
    @TypeConverters(StringListConverters::class)
    @ColumnInfo
    val tags: List<String>?,
    @ColumnInfo
    val author: String
)

class StringListConverters {
    @TypeConverter
    fun listToString(list: List<String>): String = list.joinToString(";")

    @TypeConverter
    fun stringToList(string: String): List<String> = string.split(";")
}