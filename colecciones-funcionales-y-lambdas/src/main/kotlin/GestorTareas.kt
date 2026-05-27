/**
 * Ejercicio 2: Find, Any y All
 *
 * Implementa los métodos de esta clase para que pasen todos los tests
 * del archivo Ejercicio2FindAnyAllTest.kt
 *
 * IMPORTANTE: No modifiques la firma de los métodos, solo implementa su lógica.
 */

data class Tarea(
    val id: Int,
    val titulo: String,
    val prioridad: Int, // 1 = baja, 2 = media, 3 = alta
    val completada: Boolean,
    val etiquetas: List<String>,
    val tiempoEstimadoHoras: Int,
)

data class EstadoProyecto(
    val hayTareasCriticasPendientes: Boolean,
    val totalHorasPendientes: Int,
    val todosLosBugsResueltos: Boolean
)

class GestorTareas {
    // Parte A: Operaciones con Find
    fun encontrarPrimeraTareaUrgente(tareas: List<Tarea>): Tarea? {
        return tareas.find{ it.prioridad == 3}
    }

    fun buscarPorId(
        tareas: List<Tarea>,
        id: Int,
    ): Tarea? {
        return tareas.find{ it.id == id}
    }

    fun encontrarTareaPendienteConEtiqueta(
        tareas: List<Tarea>,
        etiqueta: String,
    ): Tarea? {
        return tareas.find{it.completada == false }
               tareas.find{it.etiquetas.toString() == etiqueta }
    }

    // Parte B: Operaciones con Any

    fun hayTareasUrgentesPendientes(tareas: List<Tarea>): Boolean {
        return tareas.any {it.prioridad == 3 && it.completada == false}

    }

    fun hayTareasQueSuperanHoras(
        tareas: List<Tarea>,
        horasLimite: Int,
    ): Boolean {
        return tareas.any {it.tiempoEstimadoHoras < horasLimite}

    }

    fun existeTareaConEtiqueta(
        tareas: List<Tarea>,
        etiqueta: String,
    ): Boolean {
        return tareas.any {etiqueta in it.etiquetas}
    }

    // Parte C: Operaciones con All

    fun todasCompletadas(tareas: List<Tarea>): Boolean {
        return tareas.all{it.completada == true }
    }

    fun todasTienenEtiquetas(tareas: List<Tarea>): Boolean {
        return tareas.all{it.etiquetas.isNotEmpty()}
    }

    fun todasDentroDeHoras(
        tareas: List<Tarea>,
        horasMaximo: Int,
    ): Boolean {
        return tareas.all{ it.tiempoEstimadoHoras < horasMaximo}
    }

    // Parte D: Combinación de Find, Any y All

    fun proyectoListoParaEntrega(tareas: List<Tarea>): Boolean {
        return tareas.all { it.prioridad != 3 || it.completada } &&
                tareas.find { !it.completada && "blocker" in it.etiquetas } == null &&
                tareas.any { it.completada && "docs" in it.etiquetas }
    }

    fun generarResumenEstado(tareas: List<Tarea>): EstadoProyecto {
        return EstadoProyecto(
            tareas.any { it.prioridad == 3 && !it.completada },
            tareas.find { !it.completada }?.let { tareas.sumOf { if (!it.completada) it.tiempoEstimadoHoras else 0 } } ?: 0,
            tareas.all { !("bug" in it.etiquetas) || it.completada }
        )
        }
}

