package com.example.kavyakanaja.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "favourites")

object FavouriteDataStore {

    private val FAVOURITES_KEY =
        stringSetPreferencesKey("favourites")

    suspend fun saveFavourite(context: Context, poemId: Int) {
        val current = getFavourites(context).toMutableSet()
        current.add(poemId.toString())

        context.dataStore.edit {
            it[FAVOURITES_KEY] = current
        }
    }

    suspend fun removeFavourite(context: Context, poemId: Int) {
        val current = getFavourites(context).toMutableSet()
        current.remove(poemId.toString())

        context.dataStore.edit {
            it[FAVOURITES_KEY] = current
        }
    }

    suspend fun getFavourites(context: Context): Set<String> {
        val preferences = context.dataStore.data.first()
        return preferences[FAVOURITES_KEY] ?: emptySet()
    }
}