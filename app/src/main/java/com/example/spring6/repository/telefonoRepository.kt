package com.example.spring6.repository

import com.example.spring6.dataSource.restDataSource
import com.example.spring6.models.FonoDetail
import com.example.spring6.models.Results
import com.example.spring6.models.telefono
import com.example.spring6.models.telefonoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface telefonoRepository {
    suspend fun getProduct(id:Int): FonoDetail
    suspend fun deleteTelefono(toDelete: telefono)
    suspend fun getAllProducts():ArrayList<Results>
    fun getProductsDb(): Flow<List<telefono>>
    fun  gerProductDb(id:Int):telefono
}

class telefonoRepositoryImp @Inject constructor(
    private val dataSource: restDataSource,
    private val telefonoDao: telefonoDao
): telefonoRepository {
    override suspend fun getProduct(id: Int): FonoDetail {
        val Response = dataSource.getProduct(id)
        val telefono =  telefono(Response.body()!!.id, Response.body()!!.name, Response.body()!!.price, Response.body()!!.image, Response.body()!!.description, Response.body()!!.lastPrice, Response.body()!!.credit)
        telefonoDao.insert(telefono)
        val fonoDetail=FonoDetail(Response.body()!!.id, Response.body()!!.name, Response.body()!!.price, Response.body()!!.image, Response.body()!!.description, Response.body()!!.lastPrice, Response.body()!!.credit)
        return fonoDetail


    }

    override suspend fun deleteTelefono(toDelete: telefono) {

    }

    override suspend fun getAllProducts(): ArrayList<Results> {
        val response = dataSource.getProducts()
        for (result in response) {
            val telefono = telefono(result.id, result.name, result.price, result.image)
            telefonoDao.insert(telefono)
        }

        return response as ArrayList<Results>
    }

    override fun getProductsDb(): Flow<List<telefono>> = telefonoDao.getAll()

    override fun gerProductDb(id: Int): telefono = telefonoDao.getById(id)
}