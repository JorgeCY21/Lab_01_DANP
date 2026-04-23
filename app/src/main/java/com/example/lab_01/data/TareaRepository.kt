package com.example.lab_01.data

class TareaRepository(private val dataStore: DataStoreManager) {

    suspend fun getTareas(): List<Tarea> = dataStore.cargarTareas()

    suspend fun saveTareas(tareas: List<Tarea>) {
        dataStore.guardarTareas(tareas)
    }
}