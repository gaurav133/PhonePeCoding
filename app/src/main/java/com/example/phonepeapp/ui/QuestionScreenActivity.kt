package com.example.phonepeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phonepeapp.R
import com.example.phonepeapp.data.Question
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionScreenActivity : AppCompatActivity() {

    var currentIndex = 0
    val questionScreenViewModel by viewModels<QuestionScreenViewModel>()

    lateinit var optionRecyclerView: RecyclerView
    lateinit var optionAdapter: QuestionOptionAdapter
    lateinit var answerAdapter: AnswerAdapter
    lateinit var questionImageView: ImageView
    lateinit var answerRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        optionRecyclerView = findViewById(R.id.rv_options)
        answerRecyclerView = findViewById(R.id.rv_answer)

        answerRecyclerView.layoutManager = GridLayoutManager(this, 6, GridLayoutManager.HORIZONTAL, false)
        optionAdapter = QuestionOptionAdapter()
        answerRecyclerView.adapter = optionAdapter

        optionRecyclerView.layoutManager = GridLayoutManager(this, 6, GridLayoutManager.HORIZONTAL, false)
        answerAdapter = AnswerAdapter()
        optionRecyclerView.adapter = answerAdapter


        questionScreenViewModel.viewStateLiveData.observe(this) {
            renderState(it)
        }

        questionScreenViewModel.processEvent(QuestionListIntent.ViewEvent.FetchQuestions)
    }

    private fun renderState(viewState: QuestionListIntent.ViewState) {
        when(viewState) {
            is QuestionListIntent.ViewState.OnQuestionsFetched -> {
                populateUIForQuestion(viewState.listOfQues[currentIndex], viewState.listOfOptions[currentIndex])
            }
            is QuestionListIntent.ViewState.OnCorrectAnswerGiven -> {
                Toast.makeText(this, "Correct answer", Toast.LENGTH_SHORT).show()
                if (viewState.currentIndex == viewState.totalSize) {
                    Toast.makeText(this, "Game over, display result", Toast.LENGTH_SHORT).show()
                } else {
                    // Move to next.

                }
            }
            is QuestionListIntent.ViewState.OnWrongAnswerGiven -> {
                Toast.makeText(this, "Wrong answer, please try again", Toast.LENGTH_SHORT).show()
            }
            else -> {

            }
        }
    }

    private fun populateUIForQuestion(question: Question, option: List<Char>) {
        Glide.with(this).load(question.imageUrl).into(questionImageView)
        optionAdapter.updateCharList(option)
        answerAdapter.updateAnswerCharCount(question.name.length)
    }
}