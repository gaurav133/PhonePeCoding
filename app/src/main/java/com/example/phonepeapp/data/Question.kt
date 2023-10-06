package com.example.phonepeapp.data

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("img_url")
    val imageUrl : String,

    @SerializedName("name")
    val name : String,
)
