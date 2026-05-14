package com.example.kavyakanaja.utils

import android.content.Context

object FavouriteManager {

    private const val PREF_NAME = "favourites"

    fun toggle(context: Context, poemId: Int) {

        val prefs = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

        val set = prefs.getStringSet(
            "fav_ids",
            mutableSetOf()
        )?.toMutableSet() ?: mutableSetOf()

        val id = poemId.toString()

        if (set.contains(id)) {
            set.remove(id)
        } else {
            set.add(id)
        }

        prefs.edit()
            .putStringSet("fav_ids", set)
            .apply()
    }

    fun isFav(
        context: Context,
        poemId: Int
    ): Boolean {

        val prefs = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

        val set = prefs.getStringSet(
            "fav_ids",
            mutableSetOf()
        ) ?: mutableSetOf()

        return set.contains(poemId.toString())
    }

    fun getAllFavourites(
        context: Context
    ): List<Int> {

        val prefs = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

        val set = prefs.getStringSet(
            "fav_ids",
            mutableSetOf()
        ) ?: mutableSetOf()

        return set.map {
            it.toInt()
        }
    }
}