package com.example.flowexample.repositories

import android.util.Log
import com.example.flowexample.api.ApiService
import com.example.flowexample.models.User
import com.example.flowexample.utils.Result
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

class UserRepository(private val apiService: ApiService) {
    suspend fun getUserData() = flow {
        try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                val users = response.body() ?: emptyList()
                Log.d(TAG, "getUserData: ${users[0]}")
                emit(Result.Success(users))
            } else {
                emit(Result.Error(Exception(response.message())))
            }
        } catch (e: Exception) {
            emit(Result.Error(Exception(e.localizedMessage)))
        }
    }

    companion object{
        private const val TAG = "UserRepository"
    }

}