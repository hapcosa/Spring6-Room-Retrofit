package com.example.spring6.models

data class ApiResponse(
    val results: List<Results> = emptyList()
)

data class Results(
    val id: Int,
    val name:String,
    val price: Long,
    val image: String,
)

data class FonoDetail(
    val id: Int,
    val name:String,
    val price: Long,
    val image: String,
    val description: String,
    val lastPrice: Long,
    val credit: Boolean
)
