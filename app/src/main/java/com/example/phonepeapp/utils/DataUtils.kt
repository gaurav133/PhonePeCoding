package com.example.phonepeapp.utils

import android.content.Context
import java.io.IOException

import java.io.InputStream

object DataUtils {
    fun loadJSONFromAssets(fileName: String, context: Context): String {
        return context.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }
}