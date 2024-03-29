package com.moronlu18.accounts.repository

import com.moronlu18.accounts.entity.Task
import com.moronlu18.accounts.enum.TaskStatus
import com.moronlu18.accounts.enum.TypeTask
import com.moronlu18.accounts.network.ResourceList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.FieldPosition
import java.time.Instant

class TaskProvider {

    companion object {

        var taskDataSet: MutableList<Task> = mutableListOf()

        init {
            initDataSetTask()
        }

        private fun initDataSetTask() {
            taskDataSet.add(
                Task(
                    1,
                    1,
                    "Hacer la presentación",
                    TypeTask.LLAMAR,
                    TaskStatus.PENDIENTE,
                    "Preparar la presentación para la reunión de ventas",
                    Instant.parse("2023-11-15T00:00:00Z"),
                    Instant.parse("2023-11-15T00:00:00Z")
                ),
            )
            taskDataSet.add(
                Task(
                    2,
                    2,
                    "Completar informe",
                    TypeTask.LLAMAR,
                    TaskStatus.PENDIENTE,
                    "Finalizar el informe mensual de ventas y enviarlo al cliente",
                    Instant.parse("2023-11-15T00:00:00Z"),
                    Instant.parse("2023-11-15T00:00:00Z"),
                ),
            )
            taskDataSet.add(
                Task(
                    3,
                    2,
                    "Entrenamiento en línea",
                    TypeTask.VISITA,
                    TaskStatus.VENCIDA,
                    "Participar en el curso de desarrollo web en línea",
                    Instant.parse("2023-11-15T00:00:00Z"),
                    Instant.parse("2023-11-15T00:00:00Z"),
                ),
            )
        }

        suspend fun getTaskList(): ResourceList {
            return withContext(Dispatchers.IO) {
                when {
                    taskDataSet.isEmpty() -> ResourceList.Error(Exception("Lista de Tareas vacia"))
                    else -> ResourceList.Success(taskDataSet as ArrayList<Task>)
                }
            }
        }

        fun getTasks(): List<Task> {
            return taskDataSet
        }

        fun updateTasks(task: Task, position: Int){
            taskDataSet[position] = task
        }

        /**
         * Comprueba si el id de cliente está en Task
         */
        fun isCustomerReferenceTask(idCli: Int): Boolean {
            return taskDataSet.any() { it.clientID == idCli }
        }
    }
}