package com.example.spring6.models

import androidx.room.Dao

import androidx.room.Query

import androidx.room.Insert

import androidx.room.Delete
import androidx.room.OnConflictStrategy

import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface telefonoDao {
    @Query("SELECT * FROM telefono")
    fun getAll(): Flow<List<telefono>>
    @Query("SELECT * FROM telefono WHERE id = :id")
    fun getById(id: Int): telefono
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(telefono: telefono)
    @Delete
    suspend fun delete(telefono: telefono)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(telefono: telefono)


}