package com.example.phonepeapp.data

import android.content.Context

interface QuestionDataSource {
    suspend fun fetchQuestions(): List<Question>
}