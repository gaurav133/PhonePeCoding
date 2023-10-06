package com.example.phonepeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.phonepeapp.R

class QuestionOptionAdapter: RecyclerView.Adapter<QuestionOptionAdapter.QuestionOptionViewHolder>() {

    private var listOfChars: MutableList<Char> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionOptionViewHolder {
        return QuestionOptionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.option_chip, parent))
    }

    override fun getItemCount(): Int {
        return listOfChars.size
    }

    override fun onBindViewHolder(holder: QuestionOptionViewHolder, position: Int) {
        holder.bind(listOfChars[position])
    }

    fun updateCharList(listOfChar: List<Char>) {
        listOfChars = listOfChar.toMutableList();
        notifyDataSetChanged()
    }

    inner class QuestionOptionViewHolder(itemView: View): ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.btn_option_char)

        init {
            button.setOnClickListener {

            }
        }

        fun bind(char: Char) {
            button.text = char.toString()
        }
    }


}