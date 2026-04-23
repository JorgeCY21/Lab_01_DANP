package com.example.lab_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.lab_01.data.*
import com.example.lab_01.ui.AppTareas
import com.example.lab_01.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStore = DataStoreManager(this)
        val repository = TareaRepository(dataStore)

        setContent {
            var darkTheme by remember { mutableStateOf(false) }

            AppTheme(darkTheme) {
                AppTareas(
                    repository = repository,
                    onToggleTheme = { darkTheme = !darkTheme }
                )
            }
        }
    }
}