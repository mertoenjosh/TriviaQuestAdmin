package com.mertoenjosh.questprovider.domain.models

data class Question (
    val id: String,
    val question: String,
    val correctAnswer: String,
    val choices: List<String>,
    val difficulty: String,
    val category: String,
    val tags: List<String>?,
    val author: String
)

fun Question.formatCategory(): String = this.category
    .split("_")
    .joinToString(" ") { el -> el[0].uppercase() + el.slice(1 until el.length) }