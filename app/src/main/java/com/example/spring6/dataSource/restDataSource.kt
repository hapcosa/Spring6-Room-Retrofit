package com.example.spring6.dataSource

import com.example.spring6.models.FonoDetail
import com.example.spring6.models.Results

import com.example.spring6.models.telefono
import com.example.spring6.utils.constants.Companion.ENDPOINT_DETAIL
import com.example.spring6.utils.constants.Companion.ENDPOINT_PRODUCTS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface restDataSource {
    @GET(ENDPOINT_PRODUCTS)
    suspend fun getProducts(): List<Results>
    @GET(ENDPOINT_DETAIL+"{id}")
    suspend fun getProduct(
        @Path("id") iId:Int):Response<FonoDetail>
}