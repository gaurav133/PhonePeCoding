package com.example.phonepeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.phonepeapp.R

class AnswerAdapter: RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    private var answer: StringBuilder = java.lang.StringBuilder()
    private var answerCharCount: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.answer_text_box, parent))
    }

    override fun getItemCount(): Int {
        return answerCharCount
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        if (answer.isNotEmpty() && answer.length > position) {
            holder.bind(answer.get(answer.length-1))
        }
    }

    fun updateAnswerCharCount(count: Int) {
        answerCharCount = count
    }

    fun updateAnswerText(char: Char) {
        answer.append(char)
        notifyItemChanged(answer.length-1)
    }

    inner class AnswerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val answerTextBox: Button = itemView.findViewById(R.id.tv_answer_box)

        fun bind(char: Char) {
            answerTextBox.text = char.toString()
        }
    }


}