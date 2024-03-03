package com.example.repasoexamenlourdes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.repasoexamenlourdes.converter.GameGenreConverter

//En caso de dos objetos POJOS relacionados
/*
@Entity(tableName = "task", foreignKeys = [
    ForeignKey(
        entity = Customer::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("customerId"),
        onDelete = ForeignKey.RESTRICT,
        onUpdate = ForeignKey.CASCADE
    )
])
 */

@Entity(tableName = "juegos")
data class Juego(
    @PrimaryKey
    var id: Int,
    var nombre: String,
    @TypeConverters(GameGenreConverter::class)
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