package com.example.phonepeapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonepeapp.data.Question
import com.example.phonepeapp.data.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class QuestionScreenViewModel @Inject constructor(val repository: QuestionRepository): ViewModel() {

    private val optionList: MutableList<List<Char>> = mutableListOf()
    val viewStateLiveData: MutableLiveData<QuestionListIntent.ViewState> = MutableLiveData()

    fun processEvent(viewEvent: QuestionListIntent.ViewEvent) {
        when(viewEvent) {
            is QuestionListIntent.ViewEvent.FetchQuestions -> {
                fetchQuestions()
            }
            is QuestionListIntent.ViewEvent.CheckAnswer -> {
                if (viewEvent.guess == viewEvent.answer) {
                    // Move to next question
                    viewStateLiveData.value = QuestionListIntent.ViewState.OnCorrectAnswerGiven(viewEvent.currentIndex + 1, viewEvent.totalSize)
                } else {
                    viewStateLiveData.value = QuestionListIntent.ViewState.OnWrongAnswerGiven(viewEvent.guess, viewEvent.answer)
                }
            }
        }
    }

    private fun fetchQuestions() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.fetchQuestions()
            }.onSuccess {
                processQuestionData(it)
                viewStateLiveData.value = QuestionListIntent.ViewState.OnQuestionsFetched(it, optionList)
            }.onFailure {
                viewStateLiveData.value = QuestionListIntent.ViewState.OnQuestionsFetchFail(it.localizedMessage ?: "Something went wrong")
            }
        }
    }


    private fun processQuestionData(list: List<Question>) {
        for (i in 0..list.size) {
            optionList.add(getScrambledLettersWithAnswer(list[i].name))
        }
    }

    private fun getScrambledLettersWithAnswer(answer: String): List<Char> {
        val builder: StringBuilder = java.lang.StringBuilder(answer)

        for (i in 0..10) {
            builder.append(('A'..'Z').random())
        }

        val list: List<Char> = builder.toList()
        return list.shuffled()
    }

}