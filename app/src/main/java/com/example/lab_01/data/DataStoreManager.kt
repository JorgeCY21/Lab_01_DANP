package com.example.lab_01.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "tareas")

class DataStoreManager(private val context: Context) {

    private val KEY_TAREAS = stringPreferencesKey("tareas")

    suspend fun guardarTareas(tareas: List<Tarea>) {
        val json = Gson().toJson(tareas)
        context.dataStore.edit {
            it[KEY_TAREAS] = json
        }
    }

    suspend fun cargarTareas(): List<Tarea> {
        val prefs = context.dataStore.data.first()
        val json = prefs[KEY_TAREAS] ?: return emptyList()

        return Gson().fromJson(
            json,
            object : TypeToken<List<Tarea>>() {}.type
        )
    }
}