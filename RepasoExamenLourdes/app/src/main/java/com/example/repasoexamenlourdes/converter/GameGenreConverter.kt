package com.example.repasoexamenlourdes.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.repasoexamenlourdes.data.Genero

@ProvidedTypeConverter
class GameGenreConverter {

    @TypeConverter
    fun toGenreGame(value: Int) : Genero{
        return enumValues<Genero>()[value]
    }

    @TypeConverter
    fun fromGenreGame(value: Genero) : Int{
        return value.ordinal
    }

}