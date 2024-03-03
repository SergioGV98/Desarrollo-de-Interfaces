package com.example.repasoexamenlourdes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "juegos")
data class Juego(
    @PrimaryKey
    var id: Int,
    var nombre: String,
    var category: Genero
    ) {
    companion object{
        fun create(
            id: Int,
            nombre: String,
            category: Genero
        ) : Juego {
            return Juego(
                id = id,
                nombre = nombre,
                category = category
            )
        }
    }
}