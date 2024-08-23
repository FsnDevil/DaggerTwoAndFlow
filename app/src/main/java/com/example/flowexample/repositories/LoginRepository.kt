package com.example.flowexample.repositories

import com.example.flowexample.api.ApiService
import com.example.flowexample.exception.UserNotFoundException
import com.example.flowexample.utils.Result
import kotlinx.coroutines.flow.flow

class LoginRepository(private val apiService: ApiService) {

    suspend fun checkForUserAuthentication(userId:Int)= flow{
       try {
           val response=apiService.getUser(userId)
           if (response.isSuccessful){
               if (response.body()?.get(0)?.id==userId){
                   emit(Result.Success(response.body()!!))
               }else{
                   emit(Result.Error(UserNotFoundException("User Not Found")))
               }
           }else{
               emit(Result.Error(UserNotFoundException("User Not Found")))
           }
       }catch (e: Exception) {
           emit(Result.Error(Exception(e.localizedMessage)))
       }
    }

    companion object{
        private const val TAG="LoginRepository"
    }
}