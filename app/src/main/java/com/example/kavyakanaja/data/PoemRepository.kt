package com.example.kavyakanaja.data

import android.content.Context
import org.json.JSONArray

object PoemRepository {

    fun loadPoems(context: Context): List<Poem> {

        val json = context.assets.open("poems.json")
            .bufferedReader().use { it.readText() }

        val array = JSONArray(json)
        val list = mutableListOf<Poem>()

        for (i in 0 until array.length()) {

            val obj = array.getJSONObject(i)

            val wordsArray = obj.getJSONArray("difficultWords")
            val words = mutableListOf<DifficultWord>()

            for (j in 0 until wordsArray.length()) {
                val w = wordsArray.getJSONObject(j)
                words.add(DifficultWord(
                    w.getString("word"),
                    w.getString("meaning")
                ))
            }

            list.add(
                Poem(
                    id = obj.getInt("id"),
                    title = obj.getString("title"),
                    poet = obj.getString("poet"),
                    year = obj.getString("year"),
                    text = obj.getString("text"),
                    bhavartha = obj.getString("bhavartha"),
                    audioFile = obj.getString("audioFile"),
                    difficultWords = words
                )
            )
        }

        return list
    }
}