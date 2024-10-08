package com.example.spring6.models

import androidx.room.PrimaryKey
import androidx.room.Entity
import androidx.room.ColumnInfo

@Entity(tableName = "telefono")
data class telefono(
    @PrimaryKey() var id: Int = 0,
    @ColumnInfo(name  ="nombre") var nombre: String = "",
    @ColumnInfo(name = "precio")
    var precio: Long = 0,
    @ColumnInfo(name = "imagen")
    var imagen: String = "",
    @ColumnInfo(name = "descripcion")
    var descripcion: String? = "",
    @ColumnInfo(name = "ultimo precio")
    var ultimoPrecio: Long? = 0,
    @ColumnInfo(name = "credito")
    var credito: Boolean? = false
)


