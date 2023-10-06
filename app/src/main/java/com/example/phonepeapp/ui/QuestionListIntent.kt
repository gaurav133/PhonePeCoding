package com.example.phonepeapp.ui

import com.example.phonepeapp.data.Question

sealed class QuestionListIntent {

    sealed class ViewEvent {
        data object FetchQuestions: ViewEvent()
        class CheckAnswer(val guess: String, val answer: String, val currentIndex: Int, val totalSize: Int): ViewEvent()
    }

    sealed class ViewState {
        class OnQuestionsFetched(val listOfQues: List<Question>, val listOfOptions: List<List<Char>>): ViewState()
        class OnQuestionsFetchFail(val error: String): ViewState()
        class OnCorrectAnswerGiven(val currentIndex: Int, val totalSize: Int): ViewState()
        class OnWrongAnswerGiven(val guess: String, val answer: String): ViewState()
    }
}
