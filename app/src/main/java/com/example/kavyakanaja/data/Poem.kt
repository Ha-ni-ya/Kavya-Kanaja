package com.example.kavyakanaja.data

data class Poem(
    val id: Int,
    val title: String,
    val poet: String,
    val year: String,
    val text: String,
    val bhavartha: String,
    val audioFile: String,

    val difficultWords: List<DifficultWord>
)

data class DifficultWord(
    val word: String,
    val meaning: String
)