# Gestor de Tareas - Jetpack Compose

Aplicación móvil desarrollada en Kotlin utilizando Jetpack Compose para la gestión de tareas. Este proyecto tiene como objetivo aplicar los conceptos de desarrollo de interfaces declarativas, reutilización de componentes y manejo de estado en aplicaciones modernas.

---

## 1. Funcionalidades

* Crear tareas
* Editar tareas
* Eliminar tareas
* Marcar tareas como completadas
* Filtrar tareas (todas, completadas, pendientes)
* Persistencia de datos utilizando DataStore
* Cambio entre tema claro y oscuro
* Uso de componentes reutilizables

---

## 2. Arquitectura del Proyecto

El proyecto está organizado en capas para separar responsabilidades y facilitar el mantenimiento:

```id="3d7v2k"
com.example.lab_01
│
├── MainActivity.kt
├── data/
│   ├── Tarea.kt
│   ├── DataStoreManager.kt
│   │   └── TareaRepository.kt
│
├── ui/
│   ├── AppTareas.kt
│   ├── components/
│   │   ├── BotonPrimario.kt
│   │   ├── CampoTexto.kt
│   │   ├── ItemTarea.kt
│   │   ├── ListaTareas.kt
│   │   └── TarjetaBase.kt
│   └── theme/
│       └── Theme.kt
```

---

## 3. Tecnologías Utilizadas

* Kotlin
* Jetpack Compose (Material 3)
* DataStore Preferences
* Gson

---

## 4. Dependencias

```gradle id="8i2g6k"
implementation("androidx.datastore:datastore-preferences:1.0.0")
implementation("com.google.code.gson:gson:2.10.1")
```

---

## 5. Descripción del Funcionamiento

La aplicación permite al usuario registrar tareas mediante un campo de entrada. Cada tarea puede ser marcada como completada, editada o eliminada. Además, se implementa un sistema de filtrado que permite visualizar las tareas según su estado.

La persistencia de los datos se realiza mediante DataStore, almacenando la información en formato JSON. Esto permite mantener los datos incluso después de cerrar la aplicación.

El cambio de tema se gestiona mediante un estado global que alterna entre esquemas de color claro y oscuro.

---

## 6. Componentes Reutilizables

Se han desarrollado componentes independientes con el objetivo de mejorar la modularidad:

* BotonPrimario
* CampoTexto
* TarjetaBase
* ItemTarea
* ListaTareas

Estos componentes permiten construir interfaces más organizadas y reutilizables.

---

## 7. Buenas Prácticas Aplicadas

* Separación de responsabilidades en capas
* Uso de composables pequeños y reutilizables
* Aplicación del patrón State Hoisting
* Manejo declarativo del estado
* Persistencia desacoplada de la interfaz
* Código estructurado y legible

---

## 8. Posibles Mejoras

* Implementación de ViewModel y StateFlow
* Uso de Room para persistencia estructurada
* Incorporación de animaciones
* Mejora de la interfaz de usuario
* Implementación de gestos como deslizamiento para eliminar

---

## 9. Autor

Jorge Enrique Condorios Yllapuma 

---

## 10. Licencia

Uso académico.
