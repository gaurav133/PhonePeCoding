package com.example.phonepeapp.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val questionLocalDataSource: QuestionLocalDataSource
) {

    suspend fun fetchQuestions(): List<Question> {
        return questionLocalDataSource.fetchQuestions()
    }
}