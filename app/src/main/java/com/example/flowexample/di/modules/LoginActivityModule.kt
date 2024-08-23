package com.example.flowexample.di.modules

import com.example.flowexample.api.ApiService
import com.example.flowexample.di.scopes.ActivityScope
import com.example.flowexample.repositories.LoginRepository
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {

    @ActivityScope
    @Provides
    fun provideLoginRepository(apiService: ApiService)=LoginRepository(apiService)
}