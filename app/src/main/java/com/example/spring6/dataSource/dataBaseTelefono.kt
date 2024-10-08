package com.example.spring6.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spring6.models.telefono
import com.example.spring6.models.telefonoDao

@Database(
    entities = [telefono::class],
    version = 1
)
abstract class dataBaseTelefono: RoomDatabase() {
    abstract fun telefonoDao(): telefonoDao

}