package com.example.flowexample.api

import com.example.flowexample.models.NewsResponse
import com.example.flowexample.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users")
    suspend fun getUser(@Query("id") id: Int): Response<List<User>>

}