package com.example.phonepeapp.data

import android.content.Context
import com.example.phonepeapp.utils.DataUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject

class QuestionLocalDataSource @Inject constructor(@ApplicationContext val context: Context): QuestionDataSource {
    override suspend fun fetchQuestions(): List<Question> {
        val data = DataUtils.loadJSONFromAssets("questions.json", context)
        val type: Type = object: TypeToken<List<Question>>() {}.getType();
        return Gson().fromJson(data, type) as List<Question>;
    }
}