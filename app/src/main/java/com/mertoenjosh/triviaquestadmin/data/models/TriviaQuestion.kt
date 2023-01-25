package com.mertoenjosh.triviaquestadmin.data.models

import androidx.room.*
import com.mertoenjosh.triviaquestadmin.util.Constants

@Entity(tableName = Constants.QUESTIONS_TABLE)
data class TriviaQuestion(
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

fun TriviaQuestion.formatCategory(): String = this.category
    .split("_")
    .joinToString(" ") { el -> el[0].uppercase() + el.slice(1 until el.length) }