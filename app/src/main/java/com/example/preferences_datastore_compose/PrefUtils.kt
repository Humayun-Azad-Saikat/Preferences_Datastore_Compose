package com.example.preferences_datastore_compose

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PrefUtils(val context: Context) {

    private val Context.datastore by preferencesDataStore("local")

    suspend fun saveString(key:String,value: String){
        context.datastore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun getString(key: String): String?{

        return context.datastore.data.map {
            it[stringPreferencesKey(key)]
        }.first()
    }


}