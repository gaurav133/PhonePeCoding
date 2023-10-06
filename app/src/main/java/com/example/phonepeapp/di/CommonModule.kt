package com.example.phonepeapp.di

import android.app.Application
import com.example.phonepeapp.PhonePeApplication
import com.example.phonepeapp.data.QuestionDataSource
import com.example.phonepeapp.data.QuestionLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class QuestionModule {

    @Binds
    abstract fun provideQuestionDataSource(questionLocalDataSource: QuestionLocalDataSource): QuestionDataSource
}