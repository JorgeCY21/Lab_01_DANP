package com.example.lab_01.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab_01.data.Tarea
import com.example.lab_01.data.TareaRepository
import com.example.lab_01.ui.components.*

enum class Filtro { TODAS, COMPLETADAS, PENDIENTES }

@Composable
fun AppTareas(
    repository: TareaRepository,
    onToggleTheme: () -> Unit
) {
    var tareas by remember { mutableStateOf(listOf<Tarea>()) }
    var texto by remember { mutableStateOf("") }
    var contadorId by remember { mutableStateOf(0) }
    var tareaEditando by remember { mutableStateOf<Tarea?>(null) }
    var filtro by remember { mutableStateOf(Filtro.TODAS) }

    LaunchedEffect(Unit) {
        val tareasGuardadas = repository.getTareas()
        tareas = tareasGuardadas
        contadorId = if (tareasGuardadas.isEmpty()) 0 else tareasGuardadas.maxOf { it.id } + 1
    }

    LaunchedEffect(tareas) {
        repository.saveTareas(tareas)
    }

    // 🔍 Filtrado
    val tareasFiltradas = tareas.filter {
        when (filtro) {
            Filtro.TODAS -> true
            Filtro.COMPLETADAS -> it.completada
            Filtro.PENDIENTES -> !it.completada
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Gestor de Tareas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonPrimario("Cambiar tema") {
            onToggleTheme()
        }

        Spacer(modifier = Modifier.height(8.dp))

        CampoTexto(
            valor = texto,
            onValorChange = { texto = it },
            label = "Nueva tarea",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonPrimario(
            texto = if (tareaEditando != null) "Actualizar tarea" else "Agregar tarea",
            modifier = Modifier.fillMaxWidth()
        ) {
            if (texto.isNotBlank()) {

                if (tareaEditando != null) {
                    tareas = tareas.map {
                        if (it.id == tareaEditando!!.id) {
                            it.copy(titulo = texto)
                        } else it
                    }
                    tareaEditando = null
                } else {
                    tareas = tareas + Tarea(contadorId++, texto)
                }

                texto = ""
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            BotonPrimario("Todas") { filtro = Filtro.TODAS }
            BotonPrimario("Completadas") { filtro = Filtro.COMPLETADAS }
            BotonPrimario("Pendiente") { filtro = Filtro.PENDIENTES }
        }

        Spacer(modifier = Modifier.height(12.dp))

        ListaTareas(
            tareas = tareasFiltradas,
            onToggle = { tarea ->
                tareas = tareas.map {
                    if (it.id == tarea.id) {
                        it.copy(completada = !it.completada)
                    } else it
                }
            },
            onDelete = { tarea ->
                tareas = tareas.filter { it.id != tarea.id }
            },
            onEdit = { tarea ->
                texto = tarea.titulo
                tareaEditando = tarea
            }
        )
    }
}