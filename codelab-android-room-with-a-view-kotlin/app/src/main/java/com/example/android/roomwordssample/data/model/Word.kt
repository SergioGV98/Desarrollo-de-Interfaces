
package com.example.android.roomwordssample.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity (tableName = "wordtable")
data class Word (@PrimaryKey (autoGenerate = true) val id: Int,
                 @NonNull val word: String,
                 @Ignore val description: String)
